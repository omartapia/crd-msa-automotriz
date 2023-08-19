package com.pichincha.crd.automotriz.controller;

import com.pichincha.crd.automotriz.domain.Routes;
import com.pichincha.crd.automotriz.service.BrandService;
import com.pichincha.crd.automotriz.service.dto.BrandDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.pichincha.crd.automotriz.domain.Routes.ID;

@RestController
@RequestMapping(Routes.BASE_BRAND)
public class BrandController {

    private BrandService service;

    @Autowired
    public BrandController(BrandService brandService) {
        this.service = brandService;
    }

    @PutMapping

    public BrandDto create(@RequestBody @Valid BrandDto brandDto) {
        return service.create(brandDto);
    }

    @PostMapping(ID)
    @ApiResponses(value = {
            @ApiResponse(content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))})
    public BrandDto update(@PathVariable Long id, @RequestBody @Valid BrandDto brandDto) {
        return service.update(id, brandDto);
    }

    @DeleteMapping(ID)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping(ID)
    @ApiResponses(value = {
            @ApiResponse(content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))})
    public BrandDto find(@PathVariable Long id) {
        return service.find(id);
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))})
    public List<BrandDto> findAll() {
        return service.findAll();
    }
}
