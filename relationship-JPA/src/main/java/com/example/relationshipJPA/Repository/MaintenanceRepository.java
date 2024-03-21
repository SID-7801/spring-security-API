package com.example.relationshipJPA.Repository;

import com.example.relationshipJPA.Entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
}
