package com.leopoldo.bcv.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.leopoldo.bcv.dtos.Json.JsonApiResponse;
import com.leopoldo.bcv.dtos.coin.CoinCreateDto;
import com.leopoldo.bcv.mappers.CoinMapper;
import com.leopoldo.bcv.models.Coin;
import com.leopoldo.bcv.repositories.ICoinRepository;
import com.leopoldo.bcv.services.interfaces.ICoinService;

@Service
public class CoinService implements ICoinService {

    @Autowired
    private ICoinRepository coinRepository;

    @Autowired
    private CoinMapper coinMapper;

    @Override
    public JsonApiResponse findAll() {
        return JsonApiResponse.builder()
                .message(HttpStatus.OK.getReasonPhrase())
                .code(HttpStatus.OK.value())
                .data(coinMapper.toCoinSumaryDto(coinRepository.findAll()))
                .build();
    }

  
    @Override
    public JsonApiResponse save(CoinCreateDto coin) {

        return JsonApiResponse.builder()
                .message(HttpStatus.CREATED.getReasonPhrase())
                .code(HttpStatus.CREATED.value())
                .data(
                        coinMapper.toCoinSumaryDto(
                            coinRepository.save(
                                Coin.builder()
                                .name(coin.getName())
                                .code(coin.getCode())
                                .build()
                )))
                .build();
    }


}
