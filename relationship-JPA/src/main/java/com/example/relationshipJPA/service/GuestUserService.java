package com.example.relationshipJPA.service;

import com.example.relationshipJPA.entity.GuestUser;

import java.util.List;

public interface GuestUserService {

    Boolean checkInGuest(GuestUser request);

    Boolean checkOutGuest(Long id);

    List<GuestUser> giveCheckInUsers();

    List<GuestUser> viewGuestUsers();
}
