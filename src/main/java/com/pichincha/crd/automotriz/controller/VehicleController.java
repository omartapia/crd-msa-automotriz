package com.pichincha.crd.automotriz.controller;

import com.pichincha.crd.automotriz.service.VehicleService;
import com.pichincha.crd.automotriz.service.dto.BrandDto;
import com.pichincha.crd.automotriz.service.dto.VehicleDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.pichincha.crd.automotriz.domain.Routes.*;

@RestController
@RequestMapping(BASE_VEHICLES)
public class VehicleController {
    private VehicleService service;

    @Autowired
    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @GetMapping
    public List<VehicleDto> getAllVehicles() {
        return service.findAll();
    }

    @PutMapping
    public VehicleDto createVehicle(@RequestBody @Valid VehicleDto vehicle) {
        return service.createVehicle(vehicle);
    }

    @PostMapping(ID)
    public VehicleDto updateVehicle(@PathVariable Long id, @RequestBody @Valid VehicleDto updatedVehicle) {
        return service.updateVehicle(id, updatedVehicle);
    }

    @DeleteMapping(ID)
    public void deleteVehicle(@PathVariable Long id) {
         service.deleteVehicle(id);
    }

    @PostMapping(BRAND)
    @ApiResponses(value = {
            @ApiResponse(content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))})
    public List<VehicleDto> getVehiclesByBrand(@RequestBody @Valid BrandDto brand) {
        return service.getVehiclesByBrand(brand.getId());
    }

    @PostMapping(MODEL)
    @ApiResponses(value = {
            @ApiResponse(content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))})
    public List<VehicleDto> getVehiclesByBrand(@RequestParam(name = "model") String model) {
        return service.getVehiclesByModel(model);
    }

    @PostMapping(YEAR)
    @ApiResponses(value = {
            @ApiResponse(content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))})
    public List<VehicleDto> getVehiclesByYear(@RequestParam(name = "year") Integer year) {
        return service.getVehiclesByYear(year);
    }
}
