package com.example.relationshipJPA.Repository;

import com.example.relationshipJPA.Entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

    Optional<RefreshToken> findByRefreshToken(String token);
}
