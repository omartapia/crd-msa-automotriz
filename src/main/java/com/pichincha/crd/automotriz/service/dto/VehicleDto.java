package com.pichincha.crd.automotriz.service.dto;

import com.pichincha.crd.automotriz.domain.enums.VehicleState;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VehicleDto {
    private Long id;
    @NotNull
    private String plate;
    @NotNull
    private String model;
    @NotNull
    private String chassisNumber;
    @NotNull
    private BrandDto brand;
    private String type;
    @NotNull
    private int engineDisplacement;
    @NotNull
    private int age;
    @NotNull
    private double valuation;
    @NotNull
    private VehicleState state;
}