package com.example.relationshipJPA.Service.ServiceImpl;

import com.example.relationshipJPA.Entity.GuestUser;
import com.example.relationshipJPA.Entity.Status;
import com.example.relationshipJPA.Repository.GuestUserRepository;
import com.example.relationshipJPA.Service.GuestUserService;
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
        guestUser.setCheckIn(LocalDateTime.now());
        guestUser.setStatus(Status.CHECKED_IN);

        guestUserRepository.save(guestUser);
        return true;
    }

    @Override
    public Boolean checkOutGuest(String name) {
        GuestUser guestUser = guestUserRepository.findByName(name);
        guestUser.setCheckOut(LocalDateTime.now());
        guestUser.setStatus(Status.CHECKED_OUT);
        guestUserRepository.save(guestUser);
        return true;
    }

    @Override
    public List<GuestUser> giveCheckInUsers() {
        List<GuestUser> guestUser = guestUserRepository.findByStatus();
        return guestUser;
    }

    @Override
    public List<GuestUser> viewGuestUsers(){
        List<GuestUser>  guestUsers = guestUserRepository.findAll();
        return guestUsers;
    }
}
