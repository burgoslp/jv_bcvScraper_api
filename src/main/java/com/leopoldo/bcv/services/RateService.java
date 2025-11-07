package com.leopoldo.bcv.services;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.leopoldo.bcv.dtos.Json.JsonApiResponse;
import com.leopoldo.bcv.dtos.rate.RateCreateDto;
import com.leopoldo.bcv.exeptions.ApiError;
import com.leopoldo.bcv.exeptions.ApiException;
import com.leopoldo.bcv.mappers.RateMapper;
import com.leopoldo.bcv.models.Coin;
import com.leopoldo.bcv.models.Rate;
import com.leopoldo.bcv.repositories.ICoinRepository;
import com.leopoldo.bcv.repositories.IRateRepository;
import com.leopoldo.bcv.services.interfaces.IRateScrapingService;
import com.leopoldo.bcv.services.interfaces.IRateService;



@Service
public class RateService implements IRateService {

    @Autowired
    private IRateRepository rateRepository;

    @Autowired
    private ICoinRepository coinRepository;

    @Autowired
    private IRateScrapingService rateScrapingService;

    @Autowired
    private RateMapper rateMapper;

    

    @Override
    public JsonApiResponse currentRates() {

        Map<String,Double> rate= rateScrapingService.scrapeRates();
        
        RateCreateDto rateCreateDto = RateCreateDto.builder()
                .name("Dolares")
                .value(rate.get("Dolares"))
                .previousValue(0.0)
                .build();



        return JsonApiResponse.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .code(HttpStatus.OK.value())
                .data(rateMapper.toRateSumaryDto(rateRepository.findAll()))                
                .build();
    }

    @Override
    public JsonApiResponse save(RateCreateDto rateCreateDto) {
        Rate rate = Rate.builder()
                .name(rateCreateDto.getName())
                .value(rateCreateDto.getValue())
                .previousValue(rateCreateDto.getPreviousValue())
                .createAt(LocalDateTime.now())
                .build();
        
        rateRepository.save(rate);


        return JsonApiResponse.builder()
                .message(HttpStatus.CREATED.getReasonPhrase())
                .code(HttpStatus.CREATED.value())
                .data("Tasa creada exitosamente")
                .build();
    }

    @Override
    public JsonApiResponse addCoin(Long rateId, Long coinId) {
        
        Rate rate = rateRepository.findById(rateId).orElseThrow(() -> new ApiException(ApiError.RATE_BYID_NOT_FOUND) );
        Coin coin = coinRepository.findById(coinId).orElseThrow(() -> new ApiException(ApiError.COIN_BYID_NOT_FOUND));

        if(rate.getCoins().contains(coin)){
            throw new ApiException(ApiError.COIN_ALREADY_EXIST);
        }

        rate.getCoins().add(coin);
        rateRepository.save(rate);


        return JsonApiResponse.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .code(HttpStatus.OK.value())
                .data("Moneda agregada a la tasa exitosamente")
                .build();
    }

    public JsonApiResponse rateScraping() {

        Map<String,Double> rate= rateScrapingService.scrapeRates();
        
        return JsonApiResponse.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .code(HttpStatus.OK.value())
                .data(rate)
                .build();
    }


}
