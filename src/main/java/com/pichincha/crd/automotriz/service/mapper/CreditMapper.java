package com.pichincha.crd.automotriz.service.mapper;


import com.pichincha.crd.automotriz.service.dto.CreditRequestDto;
import com.pichincha.crd.automotriz.service.dto.entity.CreditRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreditMapper {

    CreditMapper INSTANCE = Mappers.getMapper(CreditMapper.class);

    @Mapping(target = "id", source = "id")
    CreditRequest dtoToEntity(CreditRequestDto dto);

    @Mapping(target = "id", source = "id")
    CreditRequestDto entityToDto(CreditRequest entity);

}
