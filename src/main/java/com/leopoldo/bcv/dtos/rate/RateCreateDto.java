package com.leopoldo.bcv.dtos.rate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RateCreateDto {

    @NotBlank
    private String name;
    @NotNull
    private Double value;
    @NotNull
    private Double previousValue;
    
}
