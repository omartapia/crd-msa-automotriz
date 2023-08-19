package com.pichincha.crd.automotriz.controller;

import com.pichincha.crd.automotriz.domain.Routes;
import com.pichincha.crd.automotriz.service.ClientYardService;
import com.pichincha.crd.automotriz.service.dto.ClientYardDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.pichincha.crd.automotriz.domain.Routes.ID;

@RestController
@RequestMapping(Routes.BASE_CLIENT_YARD)
public class ClientYardController {

    private ClientYardService service;

    @Autowired
    public ClientYardController(ClientYardService service) {
        this.service = service;
    }

    @PutMapping

    public ClientYardDto create(@RequestBody @Valid ClientYardDto dto) {
        return service.create(dto);
    }

    @PostMapping(ID)
    @ApiResponses(value = {
            @ApiResponse(content = @Content(mediaType = "application/json"))})
    public ClientYardDto update(@PathVariable Long id, @RequestBody @Valid ClientYardDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping(ID)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping(ID)
    @ApiResponses(value = {
            @ApiResponse(content = @Content(mediaType = "application/json"))})
    public ClientYardDto find(@PathVariable Long id) {
        return service.find(id);
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(content = @Content(mediaType = "application/json"))})
    public List<ClientYardDto> findAll() {
        return service.findAll();
    }
}
