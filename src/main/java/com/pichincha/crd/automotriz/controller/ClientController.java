package com.pichincha.crd.automotriz.controller;

import com.pichincha.crd.automotriz.domain.Routes;
import com.pichincha.crd.automotriz.service.ClientService;
import com.pichincha.crd.automotriz.service.dto.ClientDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

import static com.pichincha.crd.automotriz.domain.Routes.ID;

@RestController
@RequestMapping(Routes.BASE_CLIENT)
public class ClientController {
    private ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))})
    public Mono<ClientDto> create(@RequestBody @Valid ClientDto clientDto) {
        return service.create(clientDto);
    }

    @PostMapping(ID)
    @ApiResponses(value = {
            @ApiResponse(content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))})
    public Mono<ClientDto> update(@PathVariable Long id, @RequestBody @Valid ClientDto clientDto) {
        return service.update(id, clientDto);
    }

    @DeleteMapping(ID)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping(ID)
    @ApiResponses(value = {
            @ApiResponse(content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))})
    public Mono<ClientDto> find(@PathVariable Long id) {
        return service.find(id);
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))})
    public Flux<ClientDto> findAll() {
        return service.findAll();
    }
}
