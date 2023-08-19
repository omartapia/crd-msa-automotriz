package com.pichincha.crd.automotriz.service.mapper;


import com.pichincha.crd.automotriz.service.dto.YardDto;
import com.pichincha.crd.automotriz.service.dto.entity.Yard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface YardMapper {

    YardMapper INSTANCE = Mappers.getMapper(YardMapper.class);

    @Mapping(target = "id", source = "id")
    Yard dtoToEntity(YardDto dto);

    @Mapping(target = "id", source = "id")
    YardDto entityToDto(Yard entity);
}
