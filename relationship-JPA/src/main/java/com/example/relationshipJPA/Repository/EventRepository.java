package com.example.relationshipJPA.Repository;

import com.example.relationshipJPA.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(value = "SELECT * FROM Event WHERE date_from >= :fromDate AND date_to <= :toDate", nativeQuery = true)
    ResponseEntity<List<Event>> findEventsByDateFromOrDateTo(LocalDate fromDate , LocalDate toDate);
}
