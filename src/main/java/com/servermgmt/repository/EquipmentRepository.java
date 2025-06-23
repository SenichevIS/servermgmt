package com.servermgmt.repository;

import com.servermgmt.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByServerId(Long serverId);
    Optional<Equipment> findBySerialNumber(String serialNumber);

    @Query("SELECT e FROM Equipment e WHERE " +
            "LOWER(e.type) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(e.model) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(e.serialNumber) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Equipment> searchAll(@Param("query") String query);
}