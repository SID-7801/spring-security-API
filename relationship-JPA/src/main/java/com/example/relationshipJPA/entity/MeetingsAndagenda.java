package com.example.relationshipJPA.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingsAndagenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long meetingid;

    @Column(nullable = false)
    private String agenda;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    private String conclusion;

}
