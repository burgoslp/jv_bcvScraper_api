package com.leopoldo.bcv.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.leopoldo.bcv.dtos.rate.RateSumaryDto;
import com.leopoldo.bcv.models.Rate;

@Mapper(componentModel = "spring")
public interface RateMapper {
    RateSumaryDto toRateSumaryDto(Rate rate);
    List<RateSumaryDto> toRateSumaryDto(Iterable<Rate> rates);
}
