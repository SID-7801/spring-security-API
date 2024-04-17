package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.entity.GuestUser;
import com.example.relationshipJPA.service.GuestUserService;
import com.example.relationshipJPA.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lwresident/v1/guest")
public class GuestUserController {

    @Autowired
    private GuestUserService guestUserService;

    // check in guest user for guard
    @PostMapping("/new-user")
    public ResponseEntity<String> raiseEntry(@RequestBody GuestUser request) {
        if (guestUserService.checkInGuest(request)) {
            return Utils.getResponseEntity("Guest user inserted successfully", HttpStatus.OK);
        } else {
            return Utils.getResponseEntity("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // check out guest user api for guard
    @PatchMapping("/checkout/{id}")
    public ResponseEntity<String> checkOut(@PathVariable Long id) {
        if (guestUserService.checkOutGuest(id)) {
            return ResponseEntity.ok("updated");
        }
        return new ResponseEntity<>("not found", HttpStatus.BAD_REQUEST);
    }

    // view all checkIn guest users for guard
    @GetMapping("/view-checkIn")
    public ResponseEntity<List<GuestUser>> viewCheckInUser() {
        List<GuestUser> view = guestUserService.giveCheckInUsers();
        return ResponseEntity.ok(view);
    }

    // view all guest users for guard and admin and secretory
    @GetMapping("/view-visitors")
    public ResponseEntity<List<GuestUser>> viewVisitors() {
        List<GuestUser> guestUserList = guestUserService.viewGuestUsers();
        return ResponseEntity.ok(guestUserList);
    }

}
