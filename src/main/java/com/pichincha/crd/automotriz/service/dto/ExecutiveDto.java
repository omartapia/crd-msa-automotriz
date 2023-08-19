package com.pichincha.crd.automotriz.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ExecutiveDto {
    @NotNull
    private Long id;
    private String identification;
    private String names;
    private String lastName;
    private String address;
    private String phone;
    private String cellPhone;
    private Long yardId;
    private String age;
}
