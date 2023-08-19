package com.pichincha.crd.automotriz.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientYardDto {

    private Long id;

    private ClientDto client;

    private YardDto yard;

    private Date assignmentDate;
}
