package com.example.relationshipJPA.Repository;

import com.example.relationshipJPA.Entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback , Long> {
}
