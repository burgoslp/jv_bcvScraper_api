package com.leopoldo.bcv.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.leopoldo.bcv.dtos.coin.CoinSumaryDto;
import com.leopoldo.bcv.models.Coin;

@Mapper(componentModel = "spring")
public interface CoinMapper {
    CoinSumaryDto toCoinSumaryDto(Coin coin);
    List<CoinSumaryDto> toCoinSumaryDto(Iterable<Coin> coins);
}
