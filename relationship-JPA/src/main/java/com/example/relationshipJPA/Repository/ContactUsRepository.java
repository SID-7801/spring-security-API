package com.example.relationshipJPA.Repository;

import com.example.relationshipJPA.Entity.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactUsRepository extends JpaRepository<ContactUs, Long> {
}
