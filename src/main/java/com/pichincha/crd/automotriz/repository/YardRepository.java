package com.pichincha.crd.automotriz.repository;

import com.pichincha.crd.automotriz.repository.constants.Queries;
import com.pichincha.crd.automotriz.service.dto.entity.Yard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.util.ReactiveWrapperConverters;
import org.springframework.stereotype.Repository;

@Repository
public interface YardRepository extends JpaRepository<Yard, Long> {

    @Query(Queries.FIND_YARD_BY_NAME)
    Yard findByName(@Param("name") String name);
}
