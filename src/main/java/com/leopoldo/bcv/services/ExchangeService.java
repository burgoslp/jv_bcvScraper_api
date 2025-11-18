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
import com.leopoldo.bcv.exeptions.ApiError;
import com.leopoldo.bcv.exeptions.ApiException;
import com.leopoldo.bcv.models.Coin;
import com.leopoldo.bcv.models.Exchange;
import com.leopoldo.bcv.models.Rate;
import com.leopoldo.bcv.repositories.ICoinRepository;
import com.leopoldo.bcv.repositories.IExchangeRepository;
import com.leopoldo.bcv.repositories.IRateRepository;
import com.leopoldo.bcv.services.interfaces.IExchangeService;
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
                            .build());
        });

        return JsonApiResponse.builder()
                            .code(HttpStatus.OK.value())
                            .message(HttpStatus.OK.getReasonPhrase())
                            .data(exchangeList)
                            .build();
    }

    private void currentExchange(){

        Map<String,Double> rateScraping =rateScrapingService.scrapeRates();
        Rate rate = rateRepository.findByName("BCV").orElseThrow(()-> new ApiException(ApiError.RATE_BYNAME_NOT_FOUND));

        rateScraping.forEach((key, currentValue) ->{
            Coin coin= coinRepository.findByName(key).orElseThrow(()-> new ApiException(ApiError.COIN_BYNAME_NOT_FOUND));

            Optional<Exchange> exchangeByCoinName=exchangeRepository.findByCoinAndRate(coin, rate);

            exchangeByCoinName.ifPresentOrElse(exchange->{

                if(!exchange.getValue().equals(currentValue)){
                    
                    exchange.setPreviousValue(exchange.getValue()); 
                    exchange.setValue(currentValue); 
                    exchange.setCreateAt(LocalDateTime.now());
                    
                    exchangeRepository.save(exchange);
                }

            }, () ->{
                throw new ApiException(ApiError.COIN_BYNAME_NOT_FOUND_IN_EXCHANGE);
            });
            
        });

    }

}
