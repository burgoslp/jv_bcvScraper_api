package com.leopoldo.bcv.services.interfaces;

import com.leopoldo.bcv.dtos.Json.JsonApiResponse;

public interface IExchangeService {
    JsonApiResponse findAll();
    JsonApiResponse findByCoinName(String coinName);
    void currentExchange();
}
