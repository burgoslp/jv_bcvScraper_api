package com.leopoldo.bcv.dtos.history;

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
    private Double value;
    @NotNull
    private Double previousValue;
    @NotNull
    private LocalDateTime createAt; 
}
