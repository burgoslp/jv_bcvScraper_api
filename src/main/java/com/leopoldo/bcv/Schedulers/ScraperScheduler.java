package com.leopoldo.bcv.Schedulers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.leopoldo.bcv.services.ExchangeService;
import com.leopoldo.bcv.services.interfaces.IExchangeService;

@Component
public class ScraperScheduler {
    private static final Logger log = LoggerFactory.getLogger(ScraperScheduler.class);

    private final IExchangeService exchangeService;

    public ScraperScheduler(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @Scheduled(cron = "${scraper.schedule.cron}", zone = "${scraper.schedule.zone:UTC}")
    public void runScraper() {
        log.info("Scheduled scraper inicia su ejecución");
        try {
            exchangeService.currentExchange(); 
            log.info("Scheduled scraper finaliza su ejecución");
        } catch (Exception e) {
            
            log.error("Scheduled scraper finaliza con errores", e);
        }
    }
}
