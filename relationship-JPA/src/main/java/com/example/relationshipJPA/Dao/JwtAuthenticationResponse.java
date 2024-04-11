package com.example.relationshipJPA.Dao;


import com.example.relationshipJPA.Entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class JwtAuthenticationResponse {

    private String token;

    @Enumerated(EnumType.STRING)
    private Role role;
    private Long userId;

//    private String refreshToken;
}

