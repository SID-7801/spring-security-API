package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.Entity.Member;
import com.example.relationshipJPA.Service.AdminService;
import com.example.relationshipJPA.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lwresident/v1/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // view all members of society
    @GetMapping("/members")
    public List<Member> viewMembers() {
        return adminService.getMembers();
    }

    // delete api for deleting members from database
    @DeleteMapping("/delete-member/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id)
    {
        if (adminService.deleteMember(id))
            return Utils.getResponseEntity("User deleted successfully!", HttpStatus.OK);
        else
            return Utils.getResponseEntity("Internal server error!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // view approvals api for admin
    @GetMapping("/requests")
    public List<Member> viewNotApprovedUsers() {
        return adminService.viewNotApprovedUsers();
    }

    // approve users api
    @PatchMapping("/requests/approve/{id}")
    public ResponseEntity<String> approveUser(@PathVariable Long id) {
        if (adminService.approveUser(id))
            return Utils.getResponseEntity("User approved successfully!", HttpStatus.OK);
        else
            return Utils.getResponseEntity("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // decline user api for declining request of user for specific role
    @PatchMapping("/requests/decline/{id}")
    public ResponseEntity<String> declineUser(@PathVariable Long id) {
        if (adminService.declineUser(id))
            return Utils.getResponseEntity("User request declined!", HttpStatus.OK);
        else
            return Utils.getResponseEntity("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}