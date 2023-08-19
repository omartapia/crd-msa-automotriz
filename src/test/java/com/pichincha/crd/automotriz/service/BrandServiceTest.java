package com.pichincha.crd.automotriz.service;

import com.pichincha.crd.automotriz.repository.BrandRepository;
import com.pichincha.crd.automotriz.service.dto.BrandDto;
import com.pichincha.crd.automotriz.service.dto.entity.Brand;
import com.pichincha.crd.automotriz.service.impl.BrandServiceImpl;
import com.pichincha.crd.automotriz.service.mapper.BrandMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandServiceImpl brandService;


    @Test
    public void testCreateBrand() {
        BrandDto brand = new BrandDto();
        brand.setDescription("Test Brand");

        when(brandRepository.save(ArgumentMatchers.any(Brand.class)))
                .thenReturn(BrandMapper.INSTANCE.dtoToEntity(brand));
        BrandDto savedBrand = brandService.create(brand);
        assertEquals("Test Brand", savedBrand.getDescription());
    }

    @Test
    public void testGetAllBrands() {
        BrandDto brand1 = new BrandDto();
        brand1.setDescription("Brand 1");

        BrandDto brand2 = new BrandDto();
        brand2.setDescription("Brand 2");

        List<BrandDto> brands = new ArrayList<>();
        brands.add(brand1);
        brands.add(brand2);

        when(brandRepository.findAll()).thenReturn(brands.stream()
                .map(brand -> BrandMapper.INSTANCE.dtoToEntity(brand)).collect(Collectors.toList()));

        List<BrandDto> retrievedBrands = brandService.findAll();

        assertEquals(2, retrievedBrands.size());
        assertEquals("Brand 1", retrievedBrands.get(0).getDescription());
        assertEquals("Brand 2", retrievedBrands.get(1).getDescription());
    }
}
