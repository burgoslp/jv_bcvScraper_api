package com.leopoldo.bcv.dtos.history;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateHistoryDto {
    @NotNull
    private String rateName;
    @NotNull
    private String coinName; 
    @NotNull
    private BigDecimal value;
    @NotNull
    private BigDecimal previousValue;
    @NotNull
    private LocalDateTime createAt; 
}
