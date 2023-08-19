package com.pichincha.crd.automotriz.service;

import com.pichincha.crd.automotriz.service.dto.ClientYardDto;

import java.util.List;

public interface ClientYardService {


    ClientYardDto create(ClientYardDto clientYardDto);

    ClientYardDto update(Long id, ClientYardDto clientYardDto);

    void delete(Long id);

    ClientYardDto find(Long id);

    List<ClientYardDto> findAll();
}
