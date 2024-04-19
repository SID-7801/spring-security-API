package com.example.relationshipJPA.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long srNo;

    @Enumerated(value = EnumType.STRING)
    private Role requestedRole;

    private LocalDateTime requestDate;
    private LocalDateTime approvedDate;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private String approvedBy;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
