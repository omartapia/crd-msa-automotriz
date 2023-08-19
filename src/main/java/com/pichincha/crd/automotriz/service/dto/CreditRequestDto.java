package com.pichincha.crd.automotriz.service.dto;

import com.pichincha.crd.automotriz.domain.enums.CreditRequestState;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class CreditRequestDto {
    private Long id;
    @NotNull
    private ClientDto client;
    @NotNull
    private YardDto yard;
    @NotNull
    private VehicleDto vehicle;
    @NotNull
    private int termMonths;
    @NotNull
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format. Use yyyy-MM-dd.")
    private String date;
    @NotNull
    private Double installments;
    @NotNull
    private Double downPayment;
    @NotNull
    private ExecutiveDto salesExecutive;
    @NotNull
    private String observation;
    @NotNull
    private CreditRequestState state;
}
