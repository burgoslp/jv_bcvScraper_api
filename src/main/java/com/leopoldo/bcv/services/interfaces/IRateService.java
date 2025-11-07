package com.leopoldo.bcv.services.interfaces;



import com.leopoldo.bcv.dtos.Json.JsonApiResponse;
import com.leopoldo.bcv.dtos.rate.RateCreateDto;

public interface IRateService {
        JsonApiResponse currentRates();
        JsonApiResponse save(RateCreateDto rateCreateDto);
        JsonApiResponse addCoin(Long rateId, Long coinId);
        JsonApiResponse rateScraping();
}
