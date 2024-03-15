package com.example.relationshipJPA.Dao.Resquest;

import com.example.relationshipJPA.Entity.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public class GuestUserRequest {

    private String name;

    private String wing;

    private String flat;

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    private Status status;
}
