package com.pichincha.crd.automotriz.service.impl;

import com.pichincha.crd.automotriz.repository.BrandRepository;
import com.pichincha.crd.automotriz.service.BrandService;
import com.pichincha.crd.automotriz.service.dto.BrandDto;
import com.pichincha.crd.automotriz.service.dto.entity.Brand;
import com.pichincha.crd.automotriz.service.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl (BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public BrandDto create(BrandDto brandDto) {
        Brand entity = brandRepository.save(mapperToEntity(brandDto));
        return mapperToDto(entity);
    }

    @Override
    public BrandDto update(Long id, BrandDto brandDto) {
        return brandRepository.findById(id).map(entity -> {
            brandDto.setId(entity.getId());
            return brandRepository.save(mapperToEntity(brandDto));
        }).map(entity -> mapperToDto(entity))
                .orElseThrow(() -> new EntityNotFoundException("Brand not found."));
    }

    @Override
    public void delete(Long id) {
        brandRepository.findById(id)
                .map(entity -> {
                    brandRepository.delete(entity);
                    return entity;})
                .orElseThrow(() -> new EntityNotFoundException("Brand not found."));
    }

    @Override
    public BrandDto find(Long id) {
        return brandRepository.findById(id)
                .map(entity -> mapperToDto(entity))
                .orElseThrow(() -> new EntityNotFoundException("Brand not found."));
    }

    @Override
    public List<BrandDto> findAll() {
        return brandRepository.findAll().stream().map(entity -> mapperToDto(entity))
                .collect(Collectors.toList());
    }

    private BrandDto mapperToDto(Brand vehicle) {
        return BrandMapper.INSTANCE.entityToDto(vehicle);
    }

    private Brand mapperToEntity(BrandDto vehicle) {
        return BrandMapper.INSTANCE.dtoToEntity(vehicle);
    }
}
