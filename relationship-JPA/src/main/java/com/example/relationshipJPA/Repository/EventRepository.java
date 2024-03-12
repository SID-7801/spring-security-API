package com.example.relationshipJPA.Repository;

import com.example.relationshipJPA.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
