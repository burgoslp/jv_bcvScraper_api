package com.leopoldo.bcv.services.interfaces;
import java.math.BigDecimal;
import java.util.Map;

public interface IRateScrapingService {
    Map<String, BigDecimal> scrapeRates() ;
}
