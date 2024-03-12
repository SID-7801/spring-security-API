package com.example.relationshipJPA.Service;

import com.example.relationshipJPA.Entity.Complain;
import com.example.relationshipJPA.Entity.Event;

import java.util.List;

public interface EventService {

    String RaiseEvent(Event request , String userName);

    List<Event> getAllEvents();

    Event updateEvent(Event request , long id);

    void deleteEvent(long id);

}
