package com.leopoldo.bcv.mappers;
import java.util.List;

import org.mapstruct.Mapper;

import com.leopoldo.bcv.dtos.history.HistorySumaryDto;
import com.leopoldo.bcv.models.History;

@Mapper(componentModel = "spring")
public interface HistoryMapper {

   HistorySumaryDto toDto(History history);
   List<HistorySumaryDto> toDto(List<History> history);
}
