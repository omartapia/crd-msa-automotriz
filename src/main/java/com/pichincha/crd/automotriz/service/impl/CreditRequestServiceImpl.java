package com.pichincha.crd.automotriz.service.impl;

import com.pichincha.crd.automotriz.domain.enums.CreditRequestState;
import com.pichincha.crd.automotriz.exceptions.ConflictException;
import com.pichincha.crd.automotriz.repository.CreditRequestRepository;
import com.pichincha.crd.automotriz.service.ClientYardService;
import com.pichincha.crd.automotriz.service.CreditRequestService;
import com.pichincha.crd.automotriz.service.dto.*;
import com.pichincha.crd.automotriz.service.dto.entity.CreditRequest;
import com.pichincha.crd.automotriz.service.dto.entity.Vehicle;
import com.pichincha.crd.automotriz.service.mapper.CreditMapper;
import com.pichincha.crd.automotriz.service.mapper.VehicleMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditRequestServiceImpl implements CreditRequestService {

    private CreditRequestRepository creditRequestRepository;

    private ExecutiveServiceImpl executiveService;

    private ClientYardService clientYardService;

    @Autowired
    public CreditRequestServiceImpl(CreditRequestRepository creditRequestRepository,
                                    ExecutiveServiceImpl executiveService,
                                    ClientYardService clientYardService) {
        this.creditRequestRepository = creditRequestRepository;
        this.executiveService = executiveService;
        this.clientYardService = clientYardService;
    }

    @Override
    public boolean existsByVehicle(@NotNull Vehicle vehicle) {
        return creditRequestRepository.existsByVehicle(vehicle.getId());
    }

    @Override
    public CreditRequestDto createCreditRequest(CreditRequestDto creditRequest) {
        creditRequestHasAssociatedInfo(creditRequest);
        CreditRequest entity = creditRequestRepository.save(mapperToEntity(creditRequest));
        ClientYardDto clientYardDto = new ClientYardDto(
                null,
                creditRequest.getClient(),
                creditRequest.getYard(),
                new Date());
        clientYardService.create(clientYardDto);
        return mapperToDto(entity);
    }

    @Override
    public void delete(Long id) {
        creditRequestRepository.findById(id)
                .map(entity -> {
                    existsCreditRequestByVehicle(mapperToDto(entity));
                    creditRequestRepository.delete(entity);
                    return entity;})
                .orElseThrow(() -> new EntityNotFoundException("Client not found."));
    }

    @Override
    public CreditRequestDto update(Long id, CreditRequestDto dto) {
        return creditRequestRepository.findById(id).map(entity -> {
                    dto.setId(entity.getId());
                    return creditRequestRepository.save(mapperToEntity(dto));
                }).map(entity -> mapperToDto(entity))
                .orElseThrow(() -> new EntityNotFoundException("Credit Request not found."));
    }

    @Override
    public List<CreditRequestDto> findAll() {
        return creditRequestRepository.findAll().stream().map(entity -> mapperToDto(entity))
                .collect(Collectors.toList());
    }

    private void creditRequestHasAssociatedInfo(CreditRequestDto creditRequest) {
        existsActiveRequestByClientAndDate(creditRequest.getClient(), creditRequest.getDate());
        existsCreditRequestByVehicle(creditRequest);
        existsSalesExecutive(creditRequest.getSalesExecutive());
    }

    @Override
    public void existsCreditRequestByVehicle(CreditRequestDto creditRequestDto) {
        boolean existsCreditRequestByVehicle = existsByVehicle(mapperToEntity(creditRequestDto.getVehicle()));
        if (existsCreditRequestByVehicle) {
            creditRequestRepository.findById(creditRequestDto.getId())
                    .map(creditRequest -> {
                        if(CreditRequestState.Registered.equals(creditRequestDto.getState())) {
                            throw new ConflictException("There is a credit requirement created in the currently registered state.");
                        }
                        return null;
                    });

        }
    }

    private void existsSalesExecutive(ExecutiveDto salesExecutive) {
        boolean existsSalesExecutive = executiveService.existById(salesExecutive.getId());
        if (!existsSalesExecutive) {
            throw new ConflictException("There is no assigned sales executive please check the executive catalog.");
        }
    }

    private void existsActiveRequestByClientAndDate(ClientDto client, String date) {
        boolean existsActiveRequest = creditRequestRepository.existsActiveRequestByClientAndDate(
                client.getId(), date, CreditRequestState.Registered);

        if (existsActiveRequest) {
            throw new ConflictException("An active request for this client already exists on the current day.");
        }
    }

    private CreditRequestDto mapperToDto(CreditRequest entity) {
        return CreditMapper.INSTANCE.entityToDto(entity);
    }
    private CreditRequest mapperToEntity(CreditRequestDto dto) {
        return CreditMapper.INSTANCE.dtoToEntity(dto);
    }
    private Vehicle mapperToEntity(VehicleDto dto) {
        return VehicleMapper.INSTANCE.dtoToEntity(dto);
    }
}
