package com.pichincha.crd.automotriz.service.impl;

import com.pichincha.crd.automotriz.domain.enums.VehicleState;
import com.pichincha.crd.automotriz.exceptions.ApplicationException;
import com.pichincha.crd.automotriz.repository.BrandRepository;
import com.pichincha.crd.automotriz.repository.VehicleRepository;
import com.pichincha.crd.automotriz.service.VehicleService;
import com.pichincha.crd.automotriz.service.dto.BrandDto;
import com.pichincha.crd.automotriz.service.dto.VehicleDto;
import com.pichincha.crd.automotriz.service.dto.entity.Brand;
import com.pichincha.crd.automotriz.service.dto.entity.Vehicle;
import com.pichincha.crd.automotriz.service.mapper.BrandMapper;
import com.pichincha.crd.automotriz.service.mapper.VehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;

    private CreditRequestServiceImpl creditRequestService;

    private BrandRepository brandRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, CreditRequestServiceImpl creditRequestService, BrandRepository brandRepository) {
        this.vehicleRepository = vehicleRepository;
        this.creditRequestService = creditRequestService;
        this.brandRepository = brandRepository;
    }

    @Override
    public List<VehicleDto> getVehiclesByModel(String model) {
        return vehicleRepository.findByModel(model)
                .stream()
                .map(vehicle -> mapperToDto(vehicle)).collect(Collectors.toList());
    }

    @Override
    public List<VehicleDto> getVehiclesByYear(Integer year) {
        return vehicleRepository.findByAge(year)
                .stream()
                .map(vehicle -> mapperToDto(vehicle))
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleDto> getVehiclesByBrand(Long brandId) {
        return vehicleRepository.findByBrand(brandId)
                .stream()
                .map(vehicle -> mapperToDto(vehicle)).collect(Collectors.toList());
    }

    @Override
    public VehicleDto createVehicle(VehicleDto vehicle) {
        boolean exists = vehicleRepository.existsByPlate(vehicle.getPlate());
        if (exists) {
            throw new IllegalArgumentException("A vehicle with the same plate already exists.");
        }
        boolean existsBrand = vehicleRepository.findByBrand(vehicle.getBrand().getId()).size() > 0 ? true:false;
        if(existsBrand) {
            throw new IllegalArgumentException(String.format("Exist a vehicle with the brand %d. " +
                            "Please add a different from the catalog.",
                    vehicle.getBrand().getId()));
        }
        hasCreditRequest(mapperToEntity(vehicle));
        vehicle.setBrand(mapperToDto(brandRepository.findById(vehicle.getBrand().getId()).orElse(null)));
        return mapperToDto(vehicleRepository.save(mapperToEntity(vehicle)));
    }

    @Override
    public VehicleDto updateVehicle(Long id, VehicleDto vehicle) throws ApplicationException {

        return vehicleRepository.findById(id)
                .map(entity -> {
                    vehicleHasAssociatedInfo(entity);
                    vehicle.setId(id);
                    vehicleRepository.save(mapperToEntity(vehicle));
                    return vehicle;
                }).orElseThrow(() -> new EntityNotFoundException("Vehicle no found."));
    }

    @Override
    public void deleteVehicle(Long vehicleId) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
        vehicle.ifPresent(entity -> {
            hasCreditRequest(entity);
            vehicleRepository.delete(entity);
        });
    }

    private void hasCreditRequest(Vehicle entity) {
        boolean hasCreditRequest = creditRequestService.existsByVehicle(entity);
        if (hasCreditRequest) {
            throw new IllegalArgumentException("The vehicle is in reserve.");
        }
    }

    private void vehicleHasAssociatedInfo(Vehicle vehicle) {
        hasCreditRequest(vehicle);
        boolean isSell = VehicleState.Selled.equals(vehicle.getState());
        if (isSell) {
            throw new IllegalArgumentException("Cannot delete vehicle cause there is selled.");
        }
    }

    @Override
    public List<VehicleDto> findAll() {
        return vehicleRepository.findAll()
                .stream()
                .map(vehicle -> mapperToDto(vehicle)).collect(Collectors.toList());
    }

    private VehicleDto mapperToDto(Vehicle vehicle) {
        return VehicleMapper.INSTANCE.entityToDto(vehicle);
    }
    private BrandDto mapperToDto(Brand brand) {
        return BrandMapper.INSTANCE.entityToDto(brand);
    }
    private Vehicle mapperToEntity(VehicleDto vehicle) {
        return VehicleMapper.INSTANCE.dtoToEntity(vehicle);
    }
}
