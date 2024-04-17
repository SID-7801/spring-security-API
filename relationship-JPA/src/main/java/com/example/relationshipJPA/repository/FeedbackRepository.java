package com.example.relationshipJPA.repository;

import com.example.relationshipJPA.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback , Long> {
}
