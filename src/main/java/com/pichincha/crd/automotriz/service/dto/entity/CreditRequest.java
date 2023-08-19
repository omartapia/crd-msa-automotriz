package com.pichincha.crd.automotriz.service.dto.entity;

import com.pichincha.crd.automotriz.domain.enums.CreditRequestState;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class CreditRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "yard_id", referencedColumnName = "id")
    private Yard yard;
    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;
    private Integer termMonths;
    private String date;
    private Double installments;
    private Double downPayment;
    @ManyToOne
    @JoinColumn(name = "executive_id", referencedColumnName = "id")
    private Executive salesExecutive;
    private String observation;
    @Enumerated(EnumType.STRING)
    private CreditRequestState state;
}
