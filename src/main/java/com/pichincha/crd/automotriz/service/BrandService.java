package com.pichincha.crd.automotriz.service;

import com.pichincha.crd.automotriz.service.dto.BrandDto;

import java.util.List;

public interface BrandService {


    BrandDto create(BrandDto brandDto);

    BrandDto update(Long id, BrandDto brandDto);

    void delete(Long id);

    BrandDto find(Long id);

    List<BrandDto> findAll();
}
