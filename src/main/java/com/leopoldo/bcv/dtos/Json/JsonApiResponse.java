package com.leopoldo.bcv.dtos.Json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JsonApiResponse {
    private Integer code;
    private String message;
    private Object data;
}
