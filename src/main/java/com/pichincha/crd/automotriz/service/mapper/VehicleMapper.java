package com.pichincha.crd.automotriz.service.mapper;


import com.pichincha.crd.automotriz.service.dto.VehicleDto;
import com.pichincha.crd.automotriz.service.dto.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);
    @Mapping(target = "id", source = "id")
    Vehicle dtoToEntity(VehicleDto dto);

    @Mapping(target = "id", source = "id")
    VehicleDto entityToDto(Vehicle entity);

}
