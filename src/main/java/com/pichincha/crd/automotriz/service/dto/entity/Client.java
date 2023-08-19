package com.pichincha.crd.automotriz.service.dto.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
