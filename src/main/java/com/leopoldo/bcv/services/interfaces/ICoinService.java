package com.leopoldo.bcv.services.interfaces;


import com.leopoldo.bcv.dtos.Json.JsonApiResponse;
import com.leopoldo.bcv.dtos.coin.CoinCreateDto;

public interface ICoinService {

    JsonApiResponse findAll();
    JsonApiResponse save(CoinCreateDto coin);
}
