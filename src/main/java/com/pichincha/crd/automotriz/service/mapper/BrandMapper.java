package com.pichincha.crd.automotriz.service.mapper;


import com.pichincha.crd.automotriz.service.dto.BrandDto;
import com.pichincha.crd.automotriz.service.dto.ClientDto;
import com.pichincha.crd.automotriz.service.dto.entity.Brand;
import com.pichincha.crd.automotriz.service.dto.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BrandMapper {

    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    @Mapping(target = "id", source = "id")
    Brand dtoToEntity(BrandDto dto);

    @Mapping(target = "id", source = "id")
    BrandDto entityToDto(Brand entity);

    List<Brand> dtoListToEntityList(List<BrandDto> entities);
}
