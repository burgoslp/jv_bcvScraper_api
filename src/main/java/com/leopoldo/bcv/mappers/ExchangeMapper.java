package com.leopoldo.bcv.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.leopoldo.bcv.dtos.exchange.ExchangeSumaryDto;
import com.leopoldo.bcv.models.Exchange;

@Mapper(componentModel = "spring")
public interface ExchangeMapper {

    @Mapping(target = "coinName", expression = "java(exchange.getCoin().getName())")
    @Mapping(target = "rateName", expression = "java(exchange.getRate().getName())")
    @Mapping(target = "value", source = "value")
    @Mapping(target = "previousValue", source = "previousValue")
    @Mapping(target = "updateAt", source = "updateAt")
    ExchangeSumaryDto toDto(Exchange exchange);

    
    @Mapping(target = "coinName", expression = "java(exchange.getCoin().getName())")
    @Mapping(target = "rateName", expression = "java(exchange.getRate().getName())")
    @Mapping(target = "value", source = "value")
    @Mapping(target = "previousValue", source = "previousValue")
    @Mapping(target = "updateAt", source = "updateAt")
    List<ExchangeSumaryDto> toDto(List<Exchange> exchange);
}