package com.pichincha.crd.automotriz.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class YardDto {

    @NotNull
    private Long id;

    private String name;
    private String address;
    private String phone;
    private int pointOfSale;

}
