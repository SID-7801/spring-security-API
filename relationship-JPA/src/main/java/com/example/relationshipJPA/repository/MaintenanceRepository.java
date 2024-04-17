package com.example.relationshipJPA.repository;

import com.example.relationshipJPA.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Month;
import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

    @Query(value = "SELECT * from Maintenance WHERE fk_mem_id = :memId AND status = 'UNPAID'", nativeQuery = true)
    List<Maintenance> findByMemId(Long memId);

    @Query(value = "SELECT * FROM Maintenance WHERE month = :month AND fk_mem_id = :id", nativeQuery = true)
    Maintenance findLastMonthMaintenance(Month month, Long id);

}