package com.pichincha.crd.automotriz.configuration.initializers;

import com.pichincha.crd.automotriz.repository.BrandRepository;
import com.pichincha.crd.automotriz.service.dto.BrandDto;
import com.pichincha.crd.automotriz.service.dto.entity.Brand;
import com.pichincha.crd.automotriz.service.mapper.BrandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataBrandInitializer extends DataInitializer implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(DataBrandInitializer.class);

    @Autowired
    private BrandRepository repository;

    @Override
    public void run(String... args) {
        try {
            List<BrandDto> brands = getCsvToBean("data-brand.csv", BrandDto.class);
            validateDuplicates(brands,"description");
            save(BrandMapper.INSTANCE.dtoListToEntityList(brands));
            logger.info("Persisted [{}] brands.", repository.count());
        } catch (Exception exception) {
            logger.error("Error to initialize client data, error {}", exception);
            throw new RuntimeException("Brand Initializer " + exception.getMessage());
        }
    }

    private void save(List<Brand> brands) {
        brands.forEach(brand -> {
            if(!repository.existsByDescription(brand.getDescription())) {
                repository.save(brand);
            }
        });
    }
}
