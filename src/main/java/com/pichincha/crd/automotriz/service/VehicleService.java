package com.pichincha.crd.automotriz.service;

import com.pichincha.crd.automotriz.service.dto.VehicleDto;

import java.util.List;

public interface VehicleService {
    List<VehicleDto> getVehiclesByModel(String model);
    List<VehicleDto> getVehiclesByYear(Integer year);
    List<VehicleDto> getVehiclesByBrand(Long brandId);
    VehicleDto createVehicle(VehicleDto vehicle);
    VehicleDto updateVehicle(Long id, VehicleDto vehicle);
    void deleteVehicle(Long vehicleId);
    List<VehicleDto> findAll();
}
