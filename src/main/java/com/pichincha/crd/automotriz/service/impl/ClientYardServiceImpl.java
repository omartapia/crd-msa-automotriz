package com.pichincha.crd.automotriz.service.impl;

import com.pichincha.crd.automotriz.exceptions.ConflictException;
import com.pichincha.crd.automotriz.repository.ClientYardRepository;
import com.pichincha.crd.automotriz.service.ClientYardService;
import com.pichincha.crd.automotriz.service.dto.ClientYardDto;
import com.pichincha.crd.automotriz.service.dto.entity.ClientYard;
import com.pichincha.crd.automotriz.service.mapper.ClientYardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientYardServiceImpl implements ClientYardService {

    private ClientYardRepository clientYardRepository;

    @Autowired
    public ClientYardServiceImpl(ClientYardRepository clientYardRepository) {
        this.clientYardRepository = clientYardRepository;
    }

    @Override
    public ClientYardDto create(ClientYardDto clientYardDto) {
        ClientYard entity = clientYardRepository.save(mapperToEntity(clientYardDto));
        return mapperToDto(entity);
    }

    @Override
    public ClientYardDto update(Long id, ClientYardDto clientYardDto) {
        return clientYardRepository.findById(id).map(entity -> {
            clientYardDto.setId(entity.getId());
            return clientYardRepository.save(mapperToEntity(clientYardDto));
        }).map(entity -> mapperToDto(entity))
                .orElseThrow(() -> new EntityNotFoundException("ClientYard not found."));
    }

    @Override
    public void delete(Long id) {
        clientYardRepository.findById(id)
                .map(entity -> {
                    hasAssociatedInfo(entity.getClient().getId(), entity.getYard().getId());
                    clientYardRepository.delete(entity);
                    return entity;})
                .orElseThrow(() -> new EntityNotFoundException("Client Yard not found."));
    }

    private void hasAssociatedInfo(Long clientId, Long yardId) {
        boolean exists = clientYardRepository.existsCreditRequestByClientYard(clientId, yardId);
        if(exists) {
            throw new ConflictException("It is not possible to eliminate, there are credits requested Registered.");
        }
    }

    @Override
    public ClientYardDto find(Long id) {
        return clientYardRepository.findById(id)
                .map(entity -> mapperToDto(entity))
                .orElseThrow(() -> new EntityNotFoundException("ClientYard not found."));
    }

    @Override
    public List<ClientYardDto> findAll() {
        return clientYardRepository.findAll().stream().map(entity -> mapperToDto(entity))
                .collect(Collectors.toList());
    }

    private ClientYardDto mapperToDto(ClientYard clientYard) {
        return ClientYardMapper.INSTANCE.entityToDto(clientYard);
    }

    private ClientYard mapperToEntity(ClientYardDto dto) {
        return ClientYardMapper.INSTANCE.dtoToEntity(dto);
    }
}
