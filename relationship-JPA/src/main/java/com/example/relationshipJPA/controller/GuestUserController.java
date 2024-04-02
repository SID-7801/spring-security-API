package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.Entity.GuestUser;
import com.example.relationshipJPA.Exception.ResourceNotFoundException;
import com.example.relationshipJPA.Service.GuestUserService;
import com.example.relationshipJPA.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lwresident/v1/guest")
@CrossOrigin(origins = "http://localhost:3000")
public class GuestUserController {

    @Autowired
    private GuestUserService guestUserService;

    @PostMapping("/raiseEntry")
    public ResponseEntity<String> raiseEntry(@RequestBody GuestUser request) {
        if (guestUserService.checkInGuest(request))
        {
            return Utils.getResponseEntity("Guest user inserted successfully", HttpStatus.OK);
        }
        else
        {
            return Utils.getResponseEntity("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/checkout/{id}")
    public ResponseEntity<String> checkOut(@PathVariable Long id) {
        if (guestUserService.checkOutGuest(id)) {
            return ResponseEntity.ok("updated");
        }
        return new ResponseEntity<>("not found", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/viewUser")
    public ResponseEntity<List<GuestUser>> viewcheckInUser() {
        List<GuestUser> view = guestUserService.giveCheckInUsers();
        return ResponseEntity.ok(view);
    }

    @GetMapping("/viewVisitors")
    public ResponseEntity<List<GuestUser>> viewVisitors() {
        List<GuestUser> guestUserList = guestUserService.viewGuestUsers();
        return ResponseEntity.ok(guestUserList);
    }
}
