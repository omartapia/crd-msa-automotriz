package com.pichincha.crd.automotriz.controller;

import com.pichincha.crd.automotriz.domain.Routes;
import com.pichincha.crd.automotriz.service.CreditRequestService;
import com.pichincha.crd.automotriz.service.dto.CreditRequestDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.pichincha.crd.automotriz.domain.Routes.ID;

@RestController
@RequestMapping(Routes.BASE_CREDIT_REQUEST)
public class CreditRequestController {

    private CreditRequestService creditRequestService;

    @Autowired
    public CreditRequestController(CreditRequestService creditRequestService){
        this.creditRequestService = creditRequestService;
    }

    @ApiResponses(value = {
            @ApiResponse(content = @Content(mediaType = "application/json"))})
    @PutMapping
    public CreditRequestDto createCreditRequest(@RequestBody @Valid CreditRequestDto creditRequest) {
        return creditRequestService.createCreditRequest(creditRequest);
    }

    @PostMapping(ID)
    @ApiResponses(value = {
            @ApiResponse(content = @Content(mediaType = "application/json"))})
    public CreditRequestDto update(@PathVariable Long id, @RequestBody @Valid CreditRequestDto dto) {
        return creditRequestService.update(id, dto);
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(content = @Content(mediaType = "application/json"))})
    public List<CreditRequestDto> findAll() {
        return creditRequestService.findAll();
    }

    @DeleteMapping(ID)
    public void delete(@PathVariable Long id) {
        creditRequestService.delete(id);
    }
}
