package com.pichincha.crd.automotriz.service;

import com.pichincha.crd.automotriz.service.dto.CreditRequestDto;
import com.pichincha.crd.automotriz.service.dto.entity.Vehicle;

import java.util.List;

public interface CreditRequestService {

    boolean existsByVehicle(Vehicle vehicle);
    CreditRequestDto createCreditRequest(CreditRequestDto creditRequest);

    void delete(Long id);

    CreditRequestDto update(Long id, CreditRequestDto dto);

    List<CreditRequestDto> findAll();

    void existsCreditRequestByVehicle(CreditRequestDto creditRequestDto);
}
