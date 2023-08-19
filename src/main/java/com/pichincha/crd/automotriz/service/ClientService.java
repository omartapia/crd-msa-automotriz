package com.pichincha.crd.automotriz.service;

import com.pichincha.crd.automotriz.service.dto.ClientDto;

import java.util.List;

public interface ClientService {


    ClientDto create(ClientDto clientDto);

    ClientDto update(Long id, ClientDto clientDto);

    void delete(Long id);

    ClientDto find(Long id);

    List<ClientDto> findAll();
}
