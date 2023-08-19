package com.pichincha.crd.automotriz.repository;

import com.pichincha.crd.automotriz.service.dto.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    boolean existsByDescription(String description);
}
