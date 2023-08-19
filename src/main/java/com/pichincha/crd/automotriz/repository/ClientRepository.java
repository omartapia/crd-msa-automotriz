package com.pichincha.crd.automotriz.repository;

import com.pichincha.crd.automotriz.service.dto.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByIdentification(String identification);
}
