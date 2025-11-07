package com.leopoldo.bcv.services;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import com.leopoldo.bcv.services.interfaces.IRateScrapingService;
import io.github.bonigarcia.wdm.WebDriverManager;

@Service
public class RateScrapingService implements IRateScrapingService {

    @Override
    public Map<String, Double> scrapeRates() {
        Map<String, Double> result = new HashMap<>();
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1200,800");
        options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.7444.60 Safari/537.36");
    
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://www.bcv.org.ve/");
            // esperar si es necesario (reemplaza con WebDriverWait si prefieres)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            By xpath = By.xpath(
                    "/html/body/div[4]/div/div[2]/div/div[1]/div[1]/section[1]/div/div[2]/div/div[7]/div/div/div[2]/strong");
            wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));

            // Luego busca el <strong> dentro del contenedor
            String rateText = driver.findElement(xpath).getText();

            result.put("Dolares", parseAmount(rateText));

        } finally {
            driver.quit();
        }
        return result;
    }

    @Override
    public boolean scrapeAndDetectChange() {

        return false;
    }


    private Double parseAmount(String raw) {
        if (raw == null) return 0.0;

        String cleaned = raw.replaceAll("[^0-9,\\.\\-]", "").replace(",", ".");
        if (cleaned.isEmpty()) return 0.0;
        
        try {
            return Double.parseDouble(cleaned);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

}
