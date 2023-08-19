package com.pichincha.crd.automotriz.service.mapper;


import com.pichincha.crd.automotriz.service.dto.ClientDto;
import com.pichincha.crd.automotriz.service.dto.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "id", source = "id")
    Client dtoToEntity(ClientDto dto);

    @Mapping(target = "id", source = "id")
    ClientDto entityToDto(Client entity);

    List<Client> dtoListToEntityList(List<ClientDto> entities);
}
