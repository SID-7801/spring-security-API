package com.example.relationshipJPA.Service;

import com.example.relationshipJPA.Entity.GuestUser;

import java.util.List;

public interface GuestUserService {

    Boolean checkInGuest(GuestUser request);

    Boolean checkOutGuest(Long id);

    List<GuestUser> giveCheckInUsers();

    List<GuestUser> viewGuestUsers();
}
