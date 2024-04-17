package com.example.relationshipJPA.repository;

import com.example.relationshipJPA.entity.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactUsRepository extends JpaRepository<ContactUs, Long> {
}
