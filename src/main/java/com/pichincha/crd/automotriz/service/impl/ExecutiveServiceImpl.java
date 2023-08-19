package com.pichincha.crd.automotriz.service.impl;

import com.pichincha.crd.automotriz.repository.ExecutiveRepository;
import com.pichincha.crd.automotriz.service.ExecutiveService;
import com.pichincha.crd.automotriz.service.dto.ExecutiveDto;
import com.pichincha.crd.automotriz.service.dto.entity.Executive;
import com.pichincha.crd.automotriz.service.mapper.ExecutiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExecutiveServiceImpl implements ExecutiveService {

    private ExecutiveRepository executiveRepository;

    @Autowired
    public ExecutiveServiceImpl(ExecutiveRepository executiveRepository) {
        this.executiveRepository = executiveRepository;
    }

    @Override
    public boolean existById(Long executiveId) {
       return executiveRepository.existsById(executiveId);
    }

    @Override
    public List<ExecutiveDto> findAll() {
        return executiveRepository.findAll().stream().map(entity -> mapperToDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<ExecutiveDto> findByYard(Long yardId) {
        return executiveRepository.findByYardId(yardId).stream()
                .map(this::mapperToDto)
                .collect(Collectors.toList());
    }

    private ExecutiveDto mapperToDto(Executive entity) {
        return ExecutiveMapper.INSTANCE.entityToDto(entity);
    }
}
