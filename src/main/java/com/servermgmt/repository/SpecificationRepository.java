package com.servermgmt.repository;

import com.servermgmt.model.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Long> {

    List<Specification> findByEquipmentId(Long equipmentId);
}