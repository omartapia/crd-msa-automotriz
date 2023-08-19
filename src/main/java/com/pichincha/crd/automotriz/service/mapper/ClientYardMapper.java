package com.pichincha.crd.automotriz.service.mapper;


import com.pichincha.crd.automotriz.service.dto.ClientYardDto;
import com.pichincha.crd.automotriz.service.dto.entity.ClientYard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientYardMapper {

    ClientYardMapper INSTANCE = Mappers.getMapper(ClientYardMapper.class);

    @Mapping(target = "id", source = "id")
    ClientYard dtoToEntity(ClientYardDto dto);

    @Mapping(target = "id", source = "id")
    ClientYardDto entityToDto(ClientYard entity);
}
