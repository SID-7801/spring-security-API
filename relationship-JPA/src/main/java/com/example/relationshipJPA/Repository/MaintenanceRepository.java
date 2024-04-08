package com.example.relationshipJPA.Repository;

import com.example.relationshipJPA.Entity.Maintenance;
import com.example.relationshipJPA.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

    @Query(value = "select * from Maintenance where fk_mem_id = :memId and status = UNPAID", nativeQuery = true)
    List<Maintenance> findByMemId(Long memId);
}