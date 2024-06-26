package com.example.relationshipJPA.repository;

import com.example.relationshipJPA.entity.Complain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComplainRepository extends JpaRepository<Complain, Long> {

    @Query(value = "SELECT * from Complain WHERE fk_mem_id = :id", nativeQuery = true)
    List<Complain> findByfk_Mem_Id(Long id);
}
