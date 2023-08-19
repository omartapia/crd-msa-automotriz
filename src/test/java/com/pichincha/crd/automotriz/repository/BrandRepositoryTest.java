package com.pichincha.crd.automotriz.repository;

import com.pichincha.crd.automotriz.service.dto.entity.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void givenABrandWhenFindMethodIsInvokedThenReturnBrandFromDataBase() {
        Brand brand = new Brand();
        brand.setDescription("Test Description");

        Brand savedBrand = brandRepository.save(brand);
        entityManager.flush();
        entityManager.clear();

        Brand retrievedBrand = entityManager.find(Brand.class, savedBrand.getId());

        assertEquals("Test Description", retrievedBrand.getDescription());
    }

    @Test
    public void givenAListOfBrandsWhenFindAllBrandsIsInvokedThenReturnListFromDataBase() {
        Brand brand1 = new Brand();
        brand1.setDescription("Brand 1");

        Brand brand2 = new Brand();
        brand2.setDescription("Brand 2");

        entityManager.persist(brand1);
        entityManager.persist(brand2);
        entityManager.flush();

        List<Brand> retrievedBrands = brandRepository.findAll();

        assertEquals(2, retrievedBrands.size());
        assertEquals("Brand 1", retrievedBrands.get(0).getDescription());
        assertEquals("Brand 2", retrievedBrands.get(1).getDescription());
    }
}
