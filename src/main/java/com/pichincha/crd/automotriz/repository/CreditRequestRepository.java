package com.pichincha.crd.automotriz.repository;

import com.pichincha.crd.automotriz.domain.enums.CreditRequestState;
import com.pichincha.crd.automotriz.repository.constants.Queries;
import com.pichincha.crd.automotriz.service.dto.entity.CreditRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRequestRepository extends JpaRepository<CreditRequest, Long> {
    @Query(Queries.EXIST_BY_VEHICLE)
    boolean existsByVehicle(@Param("vehicleId") Long vehicleId);
    @Query(Queries.EXIST_ACTIVE_REQUEST_BY_CLIENT_AND_DATE)
    boolean existsActiveRequestByClientAndDate(@Param("clientId")Long client, @Param("date")String date, @Param("state") CreditRequestState state);
}
