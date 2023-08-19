package com.pichincha.crd.automotriz.repository;

import com.pichincha.crd.automotriz.repository.constants.Queries;
import com.pichincha.crd.automotriz.service.dto.entity.ClientYard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientYardRepository extends JpaRepository<ClientYard, Long> {

    @Query(Queries.EXIST_BY_CLIENT_YARD)
    boolean existsCreditRequestByClientYard(@Param("clientId") Long clientId, @Param("yardId") Long yardId);
}
