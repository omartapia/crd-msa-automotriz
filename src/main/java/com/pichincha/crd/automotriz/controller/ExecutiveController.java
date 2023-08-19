package com.pichincha.crd.automotriz.controller;

import com.pichincha.crd.automotriz.domain.Routes;
import com.pichincha.crd.automotriz.service.ExecutiveService;
import com.pichincha.crd.automotriz.service.dto.ExecutiveDto;
import com.pichincha.crd.automotriz.service.dto.YardDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.pichincha.crd.automotriz.domain.Routes.ID;
import static com.pichincha.crd.automotriz.domain.Routes.YARD;

@RestController
@RequestMapping(Routes.BASE_EXECUTIVES)
public class ExecutiveController {

    private ExecutiveService service;

    @Autowired
    public ExecutiveController(ExecutiveService service) {
        this.service = service;
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))})
    public List<ExecutiveDto> findAll() {
        return service.findAll();
    }

    @PostMapping(YARD + ID)
    @ApiResponses(value = {
            @ApiResponse(content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))})
    public List<ExecutiveDto> find(@RequestBody @Valid YardDto yardDto) {
        return service.findByYard(yardDto.getId());
    }
}
