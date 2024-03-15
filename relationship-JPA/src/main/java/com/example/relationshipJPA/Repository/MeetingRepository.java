package com.example.relationshipJPA.Repository;

import com.example.relationshipJPA.Entity.MeetingsAndagenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<MeetingsAndagenda, Long> {
}
