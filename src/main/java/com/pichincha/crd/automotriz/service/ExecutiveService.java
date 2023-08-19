package com.pichincha.crd.automotriz.service;

import com.pichincha.crd.automotriz.service.dto.ExecutiveDto;

import java.util.List;

public interface ExecutiveService {
    boolean existById(Long executiveId);

    List<ExecutiveDto> findAll();

    List<ExecutiveDto> findByYard(Long yardId);
}
