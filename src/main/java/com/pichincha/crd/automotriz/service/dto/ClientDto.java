package com.pichincha.crd.automotriz.service.dto;

import lombok.Data;

@Data
public class ClientDto {
    private Long id;

    private String identification;
    private String names;
    private String age;
    private String birthDate;
    private String lastName;
    private String address;
    private String phone;
    private String maritalStatus;
    private String spouseIdentification;
    private String spouseName;
    private String creditSubject;
}
