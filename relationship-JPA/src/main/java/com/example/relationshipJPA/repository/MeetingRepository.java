package com.example.relationshipJPA.repository;

import com.example.relationshipJPA.entity.MeetingsAndagenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<MeetingsAndagenda, Long> {
}
