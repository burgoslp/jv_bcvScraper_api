package com.leopoldo.bcv.dtos.rate;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RateSumaryDto {
    
    private String name;
    private Long value;
    private Long previousValue;
    private LocalDateTime createAt;
}
