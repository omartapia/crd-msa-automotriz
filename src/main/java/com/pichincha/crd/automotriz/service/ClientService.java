package com.pichincha.crd.automotriz.service;

import com.pichincha.crd.automotriz.service.dto.ClientDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {


    Mono<ClientDto> create(ClientDto clientDto);

    Mono<ClientDto> update(Long id, ClientDto clientDto);

    void delete(Long id);

    Mono<ClientDto> find(Long id);

    Flux<ClientDto> findAll();
}
