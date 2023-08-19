package com.pichincha.crd.automotriz.controller;

import com.pichincha.crd.automotriz.domain.Routes;
import com.pichincha.crd.automotriz.service.YardService;
import com.pichincha.crd.automotriz.service.dto.YardDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.pichincha.crd.automotriz.domain.Routes.ID;

@RestController
@RequestMapping(Routes.BASE_YARD)
public class YardController {

    private YardService service;

    @Autowired
    public YardController(YardService brandService) {
        this.service = brandService;
    }

    @PutMapping

    public YardDto create(@RequestBody @Valid YardDto yardDto) {
        return service.create(yardDto);
    }

    @PostMapping(ID)
    @ApiResponses(value = {
            @ApiResponse(content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))})
    public YardDto update(@PathVariable Long id, @RequestBody @Valid YardDto brandDto) {
        return service.update(id, brandDto);
    }

    @DeleteMapping(ID)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping(ID)
    @ApiResponses(value = {
            @ApiResponse(content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))})
    public YardDto find(@PathVariable Long id) {
        return service.find(id);
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))})
    public List<YardDto> findAll() {
        return service.findAll();
    }
}
