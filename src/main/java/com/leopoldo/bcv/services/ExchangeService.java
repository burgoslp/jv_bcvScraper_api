package com.leopoldo.bcv.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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


@Service
public class ExchangeService implements IExchangeService {

    @Autowired
    private IRateScrapingService rateScrapingService;

    @Autowired
    private ICoinRepository coinRepository;

    @Autowired 
    private IRateRepository rateRepository;

    @Autowired
    private IExchangeRepository exchangeRepository;

    @Autowired 
    private IHistoryService  historyService;



    @Override
    public JsonApiResponse findAll() {

        currentExchange();

        List<Exchange> exchanges =(List<Exchange>) exchangeRepository.findAll();
        List<ExchangeSumaryDto> exchangeList= new ArrayList<>();
        exchanges.forEach(exchange->{

            String coinName= coinRepository.findById(exchange.getCoin().getId()).orElseThrow(() -> new ApiException(ApiError.COIN_BYID_NOT_FOUND)).getName();
            String RateName= rateRepository.findById(exchange.getRate().getId()).orElseThrow(() -> new ApiException(ApiError.RATE_BYID_NOT_FOUND)).getName();
            
            exchangeList.add( ExchangeSumaryDto.builder()
                            .coinName(coinName)
                            .rateName(RateName)
                            .value(exchange.getValue())
                            .previousValue(exchange.getPreviousValue())
                            .updateAt(exchange.getUpdateAt())
                            .build());
        });

        return JsonApiResponse.builder()
                            .code(HttpStatus.OK.value())
                            .message(HttpStatus.OK.getReasonPhrase())
                            .data(exchangeList)
                            .build();
    }

    private void currentExchange(){

        //buscamos por nombre la tasa y lanzamos exepciones 
        Rate rate = rateRepository.findByName("BCV").orElseThrow(()-> new ApiException(ApiError.RATE_BYNAME_NOT_FOUND));

        //llamamos la función que se encarga de capturar los datos de la página oficial del banco central de venezuela
        Map<String,Double> rateScraping =rateScrapingService.scrapeRates();

        //recorremos nuestro map que contiene el nombre de las monedas capturadas y su valor (dolares, 10.00)
        rateScraping.forEach((key, currentValueWeb) ->{
            
            //buscamos por nombre la moneda y lanzamos exepciones
            Coin coin= coinRepository.findByName(key).orElseThrow(()-> new ApiException(ApiError.COIN_BYNAME_NOT_FOUND));

            //buscamos la tasa de cambio por el nombre de la tasa y la moneda 
            Optional<Exchange> exchangeByCoinName=exchangeRepository.findByCoinAndRate(coin, rate);

            exchangeByCoinName.ifPresentOrElse(exchange->{
                //verificamos si existe una diferencia entre el valor actual y el de la página
                if(!exchange.getValue().equals(currentValueWeb)){

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
