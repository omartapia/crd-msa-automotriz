package com.pichincha.crd.automotriz.service.dto.entity;

import com.pichincha.crd.automotriz.domain.enums.VehicleState;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plate;
    private String model;
    private String chassisNumber;
    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;
    private String type;
    private int engineDisplacement;
    private int age;
    private double valuation;
    @Enumerated(EnumType.STRING)
    private VehicleState state;
}