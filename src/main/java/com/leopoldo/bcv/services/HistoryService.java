package com.leopoldo.bcv.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.leopoldo.bcv.dtos.Json.JsonApiResponse;
import com.leopoldo.bcv.dtos.history.CreateHistoryDto;
import com.leopoldo.bcv.mappers.HistoryMapper;
import com.leopoldo.bcv.models.History;
import com.leopoldo.bcv.repositories.IHistoryRepository;
import com.leopoldo.bcv.services.interfaces.IHistoryService;

@Service
public class HistoryService implements IHistoryService {

    @Autowired
    private IHistoryRepository historyRepository;
    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public JsonApiResponse findAllByCreateAtBetween(String coinName,String start, String end) {

         LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.plusDays(1).atStartOfDay().minusNanos(1);

        List<History> results = historyRepository.findAllByCoinNameAndCreateAtBetween(coinName,startDateTime, endDateTime);

        System.out.println("aqui");
        return JsonApiResponse.builder()
                            .code(HttpStatus.OK.value())
                            .message(HttpStatus.OK.getReasonPhrase())
                            .data(historyMapper.toDto(results))
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
