package com.example.relationshipJPA.Service;

import com.example.relationshipJPA.Entity.GuestUser;

import java.util.List;

public interface GuestUserService {

    Boolean checkInGuest(GuestUser request);

    Boolean checkOutGuest(String name);

    List<GuestUser> giveCheckInUsers();

    List<GuestUser> viewGuestUsers();
}
