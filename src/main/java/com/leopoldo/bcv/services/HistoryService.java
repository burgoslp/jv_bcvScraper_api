package com.leopoldo.bcv.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.leopoldo.bcv.dtos.Json.JsonApiResponse;
import com.leopoldo.bcv.dtos.history.CreateHistoryDto;
import com.leopoldo.bcv.models.History;
import com.leopoldo.bcv.repositories.IHistoryRepository;
import com.leopoldo.bcv.services.interfaces.IHistoryService;

@Service
public class HistoryService implements IHistoryService {

    @Autowired
    private IHistoryRepository historyRepository;


    @Override
    public JsonApiResponse findAll() {
       

        return JsonApiResponse.builder()
                            .code(HttpStatus.OK.value())
                            .message(HttpStatus.OK.getReasonPhrase())
                            .data((List<History>)historyRepository.findAll())
                            .build();
    }

    @Override
    public void save(CreateHistoryDto createHistoryDto) {

        History history = History.builder()
                                .rateName(createHistoryDto.getRateName())
                                .coinName(createHistoryDto.getCoinName())   
                                .value(createHistoryDto.getValue())
                                .previousValue(createHistoryDto.getPreviousValue())
                                .createAt(createHistoryDto.getCreateAt())     
        .build();

        historyRepository.save(history);       
    }

}
