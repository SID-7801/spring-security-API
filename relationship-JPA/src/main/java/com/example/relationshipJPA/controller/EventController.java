package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.entity.Event;
import com.example.relationshipJPA.service.EventService;
import com.example.relationshipJPA.util.Utils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("lwresident/v1/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // create new event for all the member except guard
    @PostMapping("/newEvent")
    public ResponseEntity<String> createEvent(@RequestBody Event request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        eventService.raiseEvent(request, username);
        return ResponseEntity.ok("successfully created event");
    }

    // view all events for admin, secretory
    @GetMapping("/view-all")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    // view my events
    @GetMapping("/my-events")
    public List<Event> getMyEvents() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Long id = eventService.getMyUserId(username);

        return eventService.getMyEvents(id);
    }

    // update details for all the users
    @PatchMapping("/update/{id}")
    public ResponseEntity<Event> updateEvent(@RequestBody Event request, @PathVariable("id") Long funcId) {
        Event updatedevent = eventService.updateEvent(request, funcId);
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
        if (dateFrom.equals(dateTo)) {
            Event data = eventService.checkSingleDateAvailable(dateFrom);
            if (data == null)
                return Utils.getResponseEntity("Bookings available for this date", HttpStatus.OK);
            return Utils.getResponseEntity("Bookings is full", HttpStatus.BAD_REQUEST);
        } else {
            Event data = eventService.checkDateAvailable(dateFrom, dateTo);
            if(data == null)
                return Utils.getResponseEntity("Booking available for this date", HttpStatus.OK);
            return Utils.getResponseEntity("Booking is full ", HttpStatus.BAD_REQUEST);
        }
    }
}
