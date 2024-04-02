package com.example.relationshipJPA.Repository;

import com.example.relationshipJPA.Entity.GuestUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GuestUserRepository extends JpaRepository<GuestUser, Long> {

    @Query(value = "SELECT * FROM guest_user WHERE status LIKE 'CHECKED_IN'", nativeQuery = true)
    List<GuestUser> findByStatus();
}
