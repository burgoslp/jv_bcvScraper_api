package com.leopoldo.bcv.dtos.coin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CoinCreateDto {

    @NotBlank
    public String name;
    @NotBlank
    public String code;
}
