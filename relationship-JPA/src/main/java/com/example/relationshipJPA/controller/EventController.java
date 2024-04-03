package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.Entity.Event;
import com.example.relationshipJPA.Service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("lwresident/v1/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // create new event for all the member except guard
    @PostMapping("/newEvent")
    public ResponseEntity<String> createEvent(@RequestBody Event request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        eventService.RaiseEvent(request, username);
        return ResponseEntity.ok("successfully created event");
    }

    // view all events for admin, secretory
    @GetMapping("/view-all")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    // update details for all the users
    @PatchMapping("/update/{id}")
    public ResponseEntity<Event> updateEvent(@RequestBody Event request, @PathVariable("id") Long funcid) {
        Event updatedevent = eventService.updateEvent(request, funcid);
        return ResponseEntity.ok(updatedevent);
    }

    // delete api for admin and secretory
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable("id") Long funcid) {
        eventService.deleteEvent(funcid);
        return ResponseEntity.ok("event deleted");
    }

    // check available booking for all the users
    // not working properly
    @PostMapping("/check-bookings")
    public ResponseEntity<String> checkDate(@RequestParam LocalDate dateFrom, @RequestParam LocalDate dateTo) {
        if (eventService.checkDateAvailable(dateFrom, dateTo)) {
            return new ResponseEntity<>("Bookings available", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Booking not available for this date", HttpStatus.BAD_REQUEST);
        }
    }
}
