package com.example.relationshipJPA.service.serviceImpl;

import com.example.relationshipJPA.entity.Event;
import com.example.relationshipJPA.entity.Member;
import com.example.relationshipJPA.exception.ResourceNotFoundException;
import com.example.relationshipJPA.repository.EventRepository;
import com.example.relationshipJPA.repository.MemberRepository;
import com.example.relationshipJPA.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public String raiseEvent(Event request, String userName) {
        Member member = memberRepository.findByEmail(userName).get();

        Event event = new Event();
        event.setTitle(request.getTitle());
        event.setFuncType(request.getFuncType());
        event.setDateFrom(request.getDateFrom());
        event.setDateTo(request.getDateTo());
        event.setBookingDate(LocalDateTime.now());
        event.setMember(member);

        eventRepository.save(event);
        return "successfully created";
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event updateEvent(Event request, long id) {
        Event existingevent = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member","id",id));
        existingevent.setTitle(request.getTitle());
        existingevent.setFuncType(request.getFuncType());
        existingevent.setDateFrom(request.getDateFrom());
        existingevent.setDateTo(request.getDateTo());
        return eventRepository.save(existingevent);
    }

    @Override
    public void deleteEvent(long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member", "id", id));
        eventRepository.deleteById(id);
    }

    @Override
    public Event checkDateAvailable(LocalDate dateFrom, LocalDate dateTo) {
        return eventRepository.findEventsByDateFromOrDateTo(dateFrom, dateTo);
    }

    @Override
    public Event checkSingleDateAvailable(LocalDate dateFrom) {
        return eventRepository.findEventsByDate(dateFrom);
    }

    @Override
    public List<Event> getMyEvents(Long id)
    {
        return eventRepository.findMyEvents(id);
    }

    @Override
    public Long getMyUserId(String email)
    {
        return memberRepository.findIdByEmail(email);
    }
}