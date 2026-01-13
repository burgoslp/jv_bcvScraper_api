package com.leopoldo.bcv.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.leopoldo.bcv.dtos.Json.JsonApiResponse;
import com.leopoldo.bcv.dtos.exchange.ExchangeSumaryDto;
import com.leopoldo.bcv.dtos.history.CreateHistoryDto;
import com.leopoldo.bcv.exeptions.ApiError;
import com.leopoldo.bcv.exeptions.ApiException;
import com.leopoldo.bcv.models.Coin;
import com.leopoldo.bcv.models.Exchange;
import com.leopoldo.bcv.models.Rate;
import com.leopoldo.bcv.repositories.ICoinRepository;
import com.leopoldo.bcv.repositories.IExchangeRepository;
import com.leopoldo.bcv.repositories.IRateRepository;
import com.leopoldo.bcv.services.interfaces.IExchangeService;
import com.leopoldo.bcv.services.interfaces.IHistoryService;
import com.leopoldo.bcv.services.interfaces.IRateScrapingService;

import jakarta.transaction.Transactional;

import com.leopoldo.bcv.mappers.ExchangeMapper;


@Service
public class ExchangeService implements IExchangeService {

    private final IRateScrapingService rateScrapingService;
    private final ICoinRepository coinRepository;
    private final IRateRepository rateRepository;
    private final IExchangeRepository exchangeRepository;
    private final IHistoryService historyService;
    private final ExchangeMapper exchangeMapper;

    public ExchangeService(IRateScrapingService rateScrapingService,ICoinRepository coinRepository, IRateRepository rateRepository,IExchangeRepository exchangeRepository,IHistoryService historyService, ExchangeMapper exchangeMapper) {
        this.rateScrapingService = rateScrapingService;
        this.coinRepository = coinRepository;
        this.rateRepository = rateRepository;
        this.exchangeRepository = exchangeRepository;
        this.historyService = historyService;
        this.exchangeMapper = exchangeMapper;
    }



    @Override
    public JsonApiResponse findAll() {


        List<Exchange> exchanges =(List<Exchange>) exchangeRepository.findAll();
        List<ExchangeSumaryDto> exchangeList = exchangeMapper.toDto(exchanges);

        return JsonApiResponse.builder()
                            .code(HttpStatus.OK.value())
                            .message(HttpStatus.OK.getReasonPhrase())
                            .data(exchangeList)
                            .build();
    }

    @Transactional
    public void currentExchange(){

        //buscamos por nombre la tasa y lanzamos exepciones 
        Rate rate = rateRepository.findByName("BCV").orElseThrow(()-> new ApiException(ApiError.RATE_BYNAME_NOT_FOUND));

        //llamamos la función que se encarga de capturar los datos de la página oficial del banco central de venezuela
        Map<String,BigDecimal> rateScraping =rateScrapingService.scrapeRates();

        if (rateScraping == null || rateScraping.isEmpty()) {
            throw new ApiException(ApiError.SCRAPER_NO_DATA);
        }

        //recorremos nuestro map que contiene el nombre de las monedas capturadas y su valor (dolares, 10.00)
        rateScraping.forEach((key, currentValueWeb) ->{
            
            //buscamos por nombre la moneda y lanzamos exepciones
            Coin coin= coinRepository.findByName(key).orElseThrow(()-> new ApiException(ApiError.COIN_BYNAME_NOT_FOUND));

            //buscamos la tasa de cambio por el nombre de la tasa y la moneda 
            Optional<Exchange> exchangeByCoinName=exchangeRepository.findByCoinAndRate(coin, rate);

            exchangeByCoinName.ifPresentOrElse(exchange->{
                //verificamos si existe una diferencia entre el valor actual y el de la página
                if(exchange.getValue().compareTo(currentValueWeb) !=0){

                    //guardamos el historico 
                    historyService.save(CreateHistoryDto.builder()
                                .rateName(rate.getName())
                                .coinName(coin.getName())
                                .value(exchange.getValue())
                                .previousValue(exchange.getPreviousValue())
                                .createAt(exchange.getUpdateAt())
                                .build());

                    //actualizamos las tasas 
                    exchange.setPreviousValue(exchange.getValue()); 
                    exchange.setValue(currentValueWeb); 
                    exchange.setUpdateAt(LocalDateTime.now());
                    
                    exchangeRepository.save(exchange);
                }

            }, () ->{
                throw new ApiException(ApiError.COIN_BYNAME_NOT_FOUND_IN_EXCHANGE);
            });
            
        });

    }

}
