package com.example.relationshipJPA.service;

import com.example.relationshipJPA.entity.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    String raiseEvent(Event request , String userName);

    List<Event> getAllEvents();

    List<Event> getMyEvents(Long id);

    Event updateEvent(Event request , long id);

    void deleteEvent(long id);

    Event checkDateAvailable(LocalDate dateFrom, LocalDate dateTo);

    Event checkSingleDateAvailable(LocalDate dateFrom);

    Long getMyUserId(String email);

}
