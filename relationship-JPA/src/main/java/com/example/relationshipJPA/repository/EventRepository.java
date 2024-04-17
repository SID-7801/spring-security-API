package com.example.relationshipJPA.repository;

import com.example.relationshipJPA.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(value = "SELECT * FROM Event WHERE date_from = :fromDate AND date_to = :toDate OR :fromDate BETWEEN date_from AND date_to OR :toDate BETWEEN date_from AND date_to OR date_from BETWEEN :fromDate AND :toDate OR date_to BETWEEN :fromDate AND :toDate", nativeQuery = true)
    Event findEventsByDateFromOrDateTo(LocalDate fromDate , LocalDate toDate);
    @Query(value = "SELECT * FROM Event WHERE date_from = :fromDate OR date_to = :fromDate OR :fromDate BETWEEN date_from AND date_to", nativeQuery = true)
    Event findEventsByDate(LocalDate fromDate);

    @Query(value = "SELECT * FROM Event WHERE fk_mem_id LIKE :id", nativeQuery = true)
    List<Event> findMyEvents(Long id);
}