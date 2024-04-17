package com.example.relationshipJPA.service.serviceImpl;

import com.example.relationshipJPA.entity.GuestUser;
import com.example.relationshipJPA.entity.Status;
import com.example.relationshipJPA.repository.GuestUserRepository;
import com.example.relationshipJPA.service.GuestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GuestUserServiceImpl implements GuestUserService {

    @Autowired
    private GuestUserRepository guestUserRepository;

    @Override
    public Boolean checkInGuest(GuestUser request) {
        GuestUser guestUser = new GuestUser();
        guestUser.setName(request.getName());
        guestUser.setWing(request.getWing());
        guestUser.setFlat(request.getFlat());
        guestUser.setMobile(request.getMobile());
        guestUser.setCheckIn(LocalDateTime.now());
        guestUser.setStatus(Status.CHECKED_IN);

        guestUserRepository.save(guestUser);
        return true;
    }

    @Override
    public Boolean checkOutGuest(Long id) {
        GuestUser guestUser = guestUserRepository.findById(id).orElseThrow();
        guestUser.setCheckOut(LocalDateTime.now());
        guestUser.setStatus(Status.CHECKED_OUT);

        guestUserRepository.save(guestUser);
        return true;
    }

    @Override
    public List<GuestUser> giveCheckInUsers() {
        return guestUserRepository.findByStatus();
    }

    @Override
    public List<GuestUser> viewGuestUsers() {
        return guestUserRepository.findAll();
    }
}
