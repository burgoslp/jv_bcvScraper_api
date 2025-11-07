package com.leopoldo.bcv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leopoldo.bcv.dtos.Json.JsonApiResponse;
import com.leopoldo.bcv.dtos.rate.RateCreateDto;
import com.leopoldo.bcv.services.interfaces.IRateService;
import jakarta.validation.Valid;




@RestController
@RequestMapping("rateScraping/v1/rates")
public class RateController {

    @Autowired
    private IRateService rateService;

    @GetMapping()
    public ResponseEntity<JsonApiResponse> findAll() {
        return ResponseEntity.ok().body(rateService.currentRates());
    }

    @GetMapping("/aqui")
    public ResponseEntity<JsonApiResponse> Ratescraping() {
        return ResponseEntity.ok().body(rateService.rateScraping());
    }
    
    @PostMapping()
    public ResponseEntity<JsonApiResponse> save(@Valid @RequestBody RateCreateDto rateCreateDto) {
        
        return ResponseEntity.ok().body(rateService.save(rateCreateDto));
    }
    
    @PostMapping("/{rateId}/coin/{coinId}")
    public ResponseEntity<JsonApiResponse> addCoin(@PathVariable Long rateId, @PathVariable Long coinId) {
        
        return ResponseEntity.ok().body(rateService.addCoin(rateId, coinId));
    }
    
}
