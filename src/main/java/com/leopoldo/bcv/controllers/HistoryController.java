package com.leopoldo.bcv.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leopoldo.bcv.dtos.Json.JsonApiResponse;
import com.leopoldo.bcv.services.HistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("rateScraping/v1/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping("/coin/{coinName}/between/{start}/{end}")
    public ResponseEntity<JsonApiResponse> getMethodName(@PathVariable String coinName, @PathVariable String start, @PathVariable String end) {
        return ResponseEntity.ok().body(historyService.findAllByCreateAtBetween(coinName, start, end));
    }
    
}
