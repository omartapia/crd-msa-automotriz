package com.pichincha.crd.automotriz.service.dto;

import com.pichincha.crd.automotriz.domain.enums.VehicleState;
import lombok.Data;

@Data
public class VehicleDto {
    private Long id;
    private String plate;
    private String model;
    private String chassisNumber;
    private BrandDto brand;
    private String type;
    private int engineDisplacement;
    private int age;
    private double valuation;
    private VehicleState state;
}