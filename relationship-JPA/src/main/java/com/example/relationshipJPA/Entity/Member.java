package com.example.relationshipJPA.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)

    private String name;
    @Column(nullable = false , unique = true)

    private String email;
    @Column(nullable = false)

    private String password;
    @Column(nullable = false)

    private String wing;
    @Column(nullable = false)

    private String flat;
    @Column(nullable = false)

    private long mobile;
    @Column(nullable = false)

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "mem_id", orphanRemoval = true)
    private Event event;

    @OneToOne(mappedBy = "member")
    @JsonIgnore
    private RefreshToken refreshToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

