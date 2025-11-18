package com.leopoldo.bcv.dtos.exchange;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ExchangeSumaryDto {
    
    private String coinName;
    private String rateName;
    private Double value;
    private Double previousValue;

}
