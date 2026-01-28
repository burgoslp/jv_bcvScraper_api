package com.leopoldo.bcv.services.interfaces;



import com.leopoldo.bcv.dtos.Json.JsonApiResponse;
import com.leopoldo.bcv.dtos.history.CreateHistoryDto;

public interface IHistoryService {
    JsonApiResponse findAllByCreateAtBetween(String coinName,String start, String end);
    void save(CreateHistoryDto createHistoryDto);
}
