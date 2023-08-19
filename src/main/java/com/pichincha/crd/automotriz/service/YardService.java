package com.pichincha.crd.automotriz.service;

import com.pichincha.crd.automotriz.service.dto.YardDto;

import java.util.List;

public interface YardService {


    YardDto create(YardDto brandDto);

    YardDto update(Long id, YardDto brandDto);

    void delete(Long id);

    YardDto find(Long id);

    List<YardDto> findAll();

    YardDto findByName(String yardId);
}
