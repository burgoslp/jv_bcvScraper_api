package com.leopoldo.bcv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leopoldo.bcv.dtos.Json.JsonApiResponse;
import com.leopoldo.bcv.services.interfaces.IExchangeService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("rateScraping/v1/exchanges")
public class ExchangeController {
    @Autowired
    private IExchangeService  exchangeService;

    @GetMapping()
    public ResponseEntity<JsonApiResponse> findAll() {
        return ResponseEntity.ok().body(exchangeService.findAll());
    }
    
}
