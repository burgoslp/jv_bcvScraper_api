package com.leopoldo.bcv.exeptions;
import java.util.List;
import org.springframework.http.HttpStatus;



public enum ApiError {
    ARGUMENTS_VALIDATION_ERROR(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "Algunos de los argumentos ingresados no son correctos",List.of("")),
    RATE_BYID_NOT_FOUND(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "la tasa de cambio por id no se encontró",List.of("")),
    RATE_BYNAME_NOT_FOUND(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "la tasa de cambio por nombre no se encontró",List.of("")),
    COIN_BYID_NOT_FOUND(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "Algunos de los argumentos ingresados no son correctos",List.of("")),
    COIN_BYNAME_NOT_FOUND(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "lA moneda por nombre no se encontró",List.of("")),
    COIN_ALREADY_EXIST(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "La moneda ya está asociada a la tasa de cambio.",List.of("")),
    COIN_BYNAME_NOT_FOUND_IN_EXCHANGE(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "La moneda no existe en ninguna tasa cambiaria.",List.of("")),
    SCRAPER_NO_DATA(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "El resultado del scraping no contiene datos.",List.of(""));
    

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;
    private final List<String> data;


    private ApiError(Integer code, HttpStatus httpStatus, String message, List<String> data) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
        this.data = data;
    }


    public Integer getCode() {
        return code;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }


    public String getMessage() {
        return message;
    }


    public List<String> getData() {
        return data;
    }
}
