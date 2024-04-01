package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.Dao.Resquest.MemberDto;
import com.example.relationshipJPA.Entity.Member;
import com.example.relationshipJPA.Service.AdminService;
import com.example.relationshipJPA.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lwresident/v1/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/view-members")
    public List<Member> viewMembers()
    {
        return adminService.getMembers();
    }

    @DeleteMapping("/delete-member/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id)
    {
        if(adminService.deleteMember(id))
        {
            return Utils.getResponseEntity("User deleted successfully!", HttpStatus.OK);
        }
        else
        {
            return Utils.getResponseEntity("Internal server error!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // view approvals api for admin
    @GetMapping("/view-requests")
    public List<Member> viewNotApprovedUsers()
    {
        return adminService.viewNotApprovedUsers();
    }

}
