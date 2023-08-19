package com.pichincha.crd.automotriz.service.dto.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Executive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identification;
    private String names;
    private String lastName;
    private String address;
    private String phone;
    private String cellPhone;
    private String age;
    private Long yardId;
}
