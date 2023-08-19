package com.pichincha.crd.automotriz.service.mapper;


import com.pichincha.crd.automotriz.service.dto.ExecutiveDto;
import com.pichincha.crd.automotriz.service.dto.ExecutiveInitDto;
import com.pichincha.crd.automotriz.service.dto.entity.Executive;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ExecutiveMapper {

    ExecutiveMapper INSTANCE = Mappers.getMapper(ExecutiveMapper.class);

    @Mapping(target = "id", source = "id")
    Executive dtoToEntity(ExecutiveDto dto);

    @Mapping(target = "id", source = "id")
    ExecutiveDto entityToDto(Executive entity);


    @Mapping(target = "yardId", ignore = true)
    Executive dtoToEntity(ExecutiveInitDto dto);
    List<Executive> dtoListToEntityList(List<ExecutiveDto> entities);
}
