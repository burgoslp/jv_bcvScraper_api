package com.leopoldo.bcv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leopoldo.bcv.dtos.Json.JsonApiResponse;
import com.leopoldo.bcv.dtos.coin.CoinCreateDto;
import com.leopoldo.bcv.services.interfaces.ICoinService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("rateScraping/v1/coins")
public class CoinController {
    @Autowired
    private ICoinService coinService;

    @GetMapping()
    public ResponseEntity<JsonApiResponse> findAll() {
        return ResponseEntity.ok().body(coinService.findAll());
    }
    
    @PostMapping()
    public ResponseEntity<JsonApiResponse> postMethodName(@Valid @RequestBody CoinCreateDto coinCreateDto) {
        return ResponseEntity.ok().body(coinService.save(coinCreateDto));
    }
    
}
