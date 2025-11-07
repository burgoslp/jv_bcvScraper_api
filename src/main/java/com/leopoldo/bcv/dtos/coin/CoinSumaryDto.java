package com.leopoldo.bcv.dtos.coin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CoinSumaryDto {
    private Long id;
    private String name;
    private String code;
}
