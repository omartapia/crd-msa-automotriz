package com.pichincha.crd.automotriz.repository;

import com.pichincha.crd.automotriz.service.dto.entity.Executive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExecutiveRepository  extends JpaRepository<Executive, Long> {

    boolean existsByIdentification(String identification);

    List<Executive> findByYardId(Long yardId);
}
