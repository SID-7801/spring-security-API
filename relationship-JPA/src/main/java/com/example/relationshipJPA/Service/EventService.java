package com.example.relationshipJPA.Service;

import com.example.relationshipJPA.Entity.Complain;
import com.example.relationshipJPA.Entity.Event;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface EventService {

    String RaiseEvent(Event request , String userName);

    List<Event> getAllEvents();

    Event updateEvent(Event request , long id);

    void deleteEvent(long id);

//    ResponseEntity<List<Event>> getAllDates(Event request, Date fromDate, Date toDate);

    Boolean checkDateAvailable(LocalDate dateFrom, LocalDate dateTo);
}
