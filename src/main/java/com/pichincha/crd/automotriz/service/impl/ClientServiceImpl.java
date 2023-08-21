package com.pichincha.crd.automotriz.service.impl;

import com.pichincha.crd.automotriz.repository.ClientRepository;
import com.pichincha.crd.automotriz.service.ClientService;
import com.pichincha.crd.automotriz.service.dto.ClientDto;
import com.pichincha.crd.automotriz.service.dto.entity.Client;
import com.pichincha.crd.automotriz.service.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository repository;

    @Autowired
    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<ClientDto> create(ClientDto brandDto) {
        return Mono.just(repository.save(mapperToEntity(brandDto)))
                .map(entity -> mapperToDto(entity)) ;
    }

    @Override
    public Mono<ClientDto> update(Long id, ClientDto clientDto) {
        return Mono.just(repository.findById(id))
                .flatMap(client -> Mono.just(client
                        .orElseThrow(() -> new EntityNotFoundException("Brand not found."))))
                .flatMap(entity -> Mono.just(repository.save(mapperToEntity(clientDto))))
                .map(entity -> mapperToDto(entity));
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
    public Mono<ClientDto> find(Long id) {
        return Mono.just(repository.findById(id))
                .map(client -> client
                        .map(entity -> mapperToDto(entity))
                        .orElseThrow(() -> new EntityNotFoundException("Brand not found.")));
    }

    @Override
    public Flux<ClientDto> findAll() {
        return Flux.just(repository.findAll())
                .flatMap(clients -> Flux.fromIterable(clients.stream()
                        .map(entity -> mapperToDto(entity))
                        .collect(Collectors.toList())));

    }

    private ClientDto mapperToDto(Client client) {
        return ClientMapper.INSTANCE.entityToDto(client);
    }

    private Client mapperToEntity(ClientDto dto) {
        return ClientMapper.INSTANCE.dtoToEntity(dto);
    }
}
