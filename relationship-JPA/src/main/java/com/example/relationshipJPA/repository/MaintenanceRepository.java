package com.example.relationshipJPA.repository;

import com.example.relationshipJPA.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

    @Query(value = "select * from Maintenance where fk_mem_id = :memId and status = 'UNPAID'", nativeQuery = true)
    List<Maintenance> findByMemId(Long memId);
}