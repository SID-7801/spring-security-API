package com.example.relationshipJPA.Service.ServiceImpl;

import com.example.relationshipJPA.Entity.Event;
import com.example.relationshipJPA.Entity.Member;
import com.example.relationshipJPA.Exception.ResourceNotFoundException;
import com.example.relationshipJPA.Repository.EventRepository;
import com.example.relationshipJPA.Repository.MemberRepository;
import com.example.relationshipJPA.Service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public String RaiseEvent(Event request, String userName) {

        Member member = memberRepository.findByEmail(userName).get();

        Event event = new Event();
        event.setTitle(request.getTitle());
        event.setFuncType(request.getFuncType());
        event.setDateFrom(request.getDateFrom());
        event.setDateTo(request.getDateTo());
        event.setMem_id(member);

        eventRepository.save(event);
//        getAllDates();
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

    // doubt
    @Override
    public Event checkDateAvailable(LocalDate dateFrom, LocalDate dateTo) {
        return eventRepository.findEventsByDateFromOrDateTo(dateFrom, dateTo);
    }

    @Override
    public Event checkSingleDateAvailable(LocalDate dateFrom) {
        return eventRepository.findEventsByDate(dateFrom);
    }
}
