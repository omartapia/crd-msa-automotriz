package com.pichincha.crd.automotriz.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientYardDto {

    private Long id;

    @NotNull
    private ClientDto client;
    @NotNull
    private YardDto yard;

    private Date assignmentDate;
}
