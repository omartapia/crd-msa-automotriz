package com.pichincha.crd.automotriz.service.impl;

import com.pichincha.crd.automotriz.exceptions.ConflictException;
import com.pichincha.crd.automotriz.repository.YardRepository;
import com.pichincha.crd.automotriz.service.ExecutiveService;
import com.pichincha.crd.automotriz.service.YardService;
import com.pichincha.crd.automotriz.service.dto.ExecutiveDto;
import com.pichincha.crd.automotriz.service.dto.YardDto;
import com.pichincha.crd.automotriz.service.dto.entity.Executive;
import com.pichincha.crd.automotriz.service.dto.entity.Yard;
import com.pichincha.crd.automotriz.service.mapper.ExecutiveMapper;
import com.pichincha.crd.automotriz.service.mapper.YardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class YardServiceImpl implements YardService {

    private YardRepository repository;

    private ExecutiveService executiveService;

    @Autowired
    public YardServiceImpl(YardRepository repository, ExecutiveService executiveService) {
        this.repository = repository;
        this.executiveService =   executiveService;
    }

    @Override
    public YardDto create(YardDto yardDto) {
        Yard entity = repository.save(mapperToEntity(yardDto));
        return mapperToDto(entity);
    }

    @Override
    public YardDto update(Long id, YardDto yardDto) {
        return repository.findById(id).map(entity -> {
            yardDto.setId(entity.getId());
            return repository.save(mapperToEntity(yardDto));
        }).map(entity -> mapperToDto(entity))
                .orElseThrow(() -> new EntityNotFoundException("Yard not found."));
    }

    @Override
    public void delete(Long id) {
        repository.findById(id)
                .map(entity -> {
                    existExecutives(entity.getId());
                    repository.delete(entity);
                    return entity;
                })
                .orElseThrow(() -> new EntityNotFoundException("Yard not found."));
    }

    private void existExecutives(Long id) {
        List<ExecutiveDto> executives = executiveService.findByYard(id);
        if(executives.isEmpty()) {
            throw  new ConflictException("It cannot be removed because the patio has reference with executives.");
        }
    }

    @Override
    public YardDto find(Long id) {
        return repository.findById(id)
                .map(this::mapperToDto)
                .orElseThrow(() -> new EntityNotFoundException("Yard not found."));
    }

    @Override
    public List<YardDto> findAll() {
        return repository.findAll().stream().map(this::mapperToDto)
                .collect(Collectors.toList());
    }

    @Override
    public YardDto findByName(String name) {
        if(name != null) {
            return Optional.ofNullable(repository.findByName(name))
                    .map(this::mapperToDto)
                    .orElse(null);
        }
        return null;
    }

    private YardDto mapperToDto(Yard yard) {
        return YardMapper.INSTANCE.entityToDto(yard);
    }

    private Yard mapperToEntity(YardDto yardDto) {
        return YardMapper.INSTANCE.dtoToEntity(yardDto);
    }

    private ExecutiveDto mapperExcecutiveToDto(Executive entity) {
        return ExecutiveMapper.INSTANCE.entityToDto(entity);
    }
}
