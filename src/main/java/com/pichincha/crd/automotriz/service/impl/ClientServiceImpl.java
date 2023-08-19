package com.pichincha.crd.automotriz.service.impl;

import com.pichincha.crd.automotriz.repository.BrandRepository;
import com.pichincha.crd.automotriz.repository.ClientRepository;
import com.pichincha.crd.automotriz.service.BrandService;
import com.pichincha.crd.automotriz.service.ClientService;
import com.pichincha.crd.automotriz.service.dto.BrandDto;
import com.pichincha.crd.automotriz.service.dto.ClientDto;
import com.pichincha.crd.automotriz.service.dto.entity.Brand;
import com.pichincha.crd.automotriz.service.dto.entity.Client;
import com.pichincha.crd.automotriz.service.mapper.BrandMapper;
import com.pichincha.crd.automotriz.service.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository repository;

    @Autowired
    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClientDto create(ClientDto brandDto) {
        Client entity = repository.save(mapperToEntity(brandDto));
        return mapperToDto(entity);
    }

    @Override
    public ClientDto update(Long id, ClientDto clientDto) {
        return repository.findById(id).map(entity -> {
            clientDto.setId(entity.getId());
            return repository.save(mapperToEntity(clientDto));
        }).map(entity -> mapperToDto(entity))
                .orElseThrow(() -> new EntityNotFoundException("Brand not found."));
    }

    @Override
    public void delete(Long id) {
        repository.findById(id)
                .map(entity -> {
                    repository.delete(entity);
                    return entity;})
                .orElseThrow(() -> new EntityNotFoundException("Client not found."));
    }

    @Override
    public ClientDto find(Long id) {
        return repository.findById(id)
                .map(entity -> mapperToDto(entity))
                .orElseThrow(() -> new EntityNotFoundException("Brand not found."));
    }

    @Override
    public List<ClientDto> findAll() {
        return repository.findAll().stream().map(entity -> mapperToDto(entity))
                .collect(Collectors.toList());
    }

    private ClientDto mapperToDto(Client client) {
        return ClientMapper.INSTANCE.entityToDto(client);
    }

    private Client mapperToEntity(ClientDto dto) {
        return ClientMapper.INSTANCE.dtoToEntity(dto);
    }
}
