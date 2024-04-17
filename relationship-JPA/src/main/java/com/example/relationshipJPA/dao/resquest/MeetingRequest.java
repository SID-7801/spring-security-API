package com.example.relationshipJPA.dao.resquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingRequest {
    private String agenda;
    private LocalDate date;
    private LocalTime time;
    private String conclusion;
}
