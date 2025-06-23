package com.servermgmt.repository;

import com.servermgmt.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {
    @Query("SELECT s FROM Server s WHERE " +
            "LOWER(s.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(s.location) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Server> searchAll(@Param("query") String query);
}