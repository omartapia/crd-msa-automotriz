package com.pichincha.crd.automotriz.repository;

import com.pichincha.crd.automotriz.repository.constants.Queries;
import com.pichincha.crd.automotriz.service.dto.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query(Queries.FIND_VEHICLE_BY_BRAND)
    List<Vehicle> findByBrand(@Param("brandId") Long brandId);

    List<Vehicle> findByModel(String model);

    Boolean existsByPlate(String plate);

    Collection<Vehicle> findByAge(Integer year);

}
