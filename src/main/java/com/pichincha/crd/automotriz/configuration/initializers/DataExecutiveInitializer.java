package com.pichincha.crd.automotriz.configuration.initializers;

import com.pichincha.crd.automotriz.repository.ExecutiveRepository;
import com.pichincha.crd.automotriz.service.YardService;
import com.pichincha.crd.automotriz.service.dto.ExecutiveDto;
import com.pichincha.crd.automotriz.service.dto.ExecutiveInitDto;
import com.pichincha.crd.automotriz.service.dto.YardDto;
import com.pichincha.crd.automotriz.service.dto.entity.Client;
import com.pichincha.crd.automotriz.service.dto.entity.Executive;
import com.pichincha.crd.automotriz.service.dto.entity.Yard;
import com.pichincha.crd.automotriz.service.mapper.ExecutiveMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataExecutiveInitializer extends DataInitializer implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(DataExecutiveInitializer.class);

    @Autowired
    private ExecutiveRepository repository;

    @Autowired
    private YardService yardService;

    @Override
    public void run(String... args) {
        try {
            List<ExecutiveInitDto> executives = getCsvToBean("data-executive.csv", ExecutiveInitDto.class);
            validateDuplicates(executives, "identification");
             save(executives.stream().map(dto -> mapper(dto)).collect(Collectors.toList()));
            logger.info("Persisted [{}] executives.", repository.count());
        } catch (Exception exception) {
            logger.error("Error to initialize client data, error {}", exception);
            throw new RuntimeException("Executive Initializer " + exception.getMessage());
        }
    }

    private void save(List<Executive> executives) {
        executives.forEach(executive -> {
            repository.findById(executive.getId())
                    .ifPresent(entity -> executive.setId(entity.getId()));
                repository.save(executive);
        });
    }

    private Executive mapper(ExecutiveInitDto dto) {
        Executive executive = ExecutiveMapper.INSTANCE.dtoToEntity(dto);
        YardDto entity = yardService.findByName(dto.getYardId());
        YardDto yard = new YardDto();
        yard.setName(dto.getYardId());
        yard.setId(entity != null ? entity.getId() : null);
        yard = yardService.create(yard);
        executive.setYardId(yard.getId());
        return executive;
    }
}
