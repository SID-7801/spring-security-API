package com.example.relationshipJPA.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sr;

    @ManyToOne
    @JsonIgnore
    private Member member;

    @Enumerated(EnumType.STRING)
    private Role requestedRole;

//    private
}
