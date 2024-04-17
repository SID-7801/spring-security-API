package com.example.relationshipJPA.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long funcid;

    private String title;

    @Column(nullable = false)
    private String funcType;

    @Column(nullable = false)
    private LocalDate dateFrom;

    @Column(nullable = false)
    private LocalDate dateTo;

    @Column(nullable = false)
    private LocalDateTime bookingDate;

    @ManyToOne
    @JoinColumn(name = "fk_mem_id")
//    @JsonIgnore
    private Member member;
}
