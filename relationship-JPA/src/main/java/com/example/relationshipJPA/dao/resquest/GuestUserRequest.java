package com.example.relationshipJPA.dao.resquest;

import com.example.relationshipJPA.entity.Status;

import java.time.LocalDateTime;

public class GuestUserRequest {

    private String name;

    private String wing;

    private String flat;

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    private Status status;
}
