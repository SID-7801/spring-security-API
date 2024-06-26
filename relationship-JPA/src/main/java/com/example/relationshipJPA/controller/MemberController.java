package com.example.relationshipJPA.controller;


import com.example.relationshipJPA.dao.resquest.RoleRequestDto;
import com.example.relationshipJPA.dao.resquest.UpdateProfileDto;
import com.example.relationshipJPA.entity.Member;
import com.example.relationshipJPA.entity.RoleRequest;
import com.example.relationshipJPA.service.AuthenticationService;
import com.example.relationshipJPA.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/lwresident/v1/member")
public class MemberController {

    @Autowired
    private AuthenticationService authenticationService;

    // update user profile for all users
    @PatchMapping("/updateProfile")
    public ResponseEntity<String> updateProfile(@RequestBody UpdateProfileDto request) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (authenticationService.updateProfile(request, username)) {
            return Utils.getResponseEntity("User updated successfully", HttpStatus.OK);
        } else {
            return Utils.getResponseEntity("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // current logged in user details
    @GetMapping("/getUser")
    public Member getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return authenticationService.getUserData(username);
    }

    @PostMapping("/request-role")
    public ResponseEntity<String> requestRole(@RequestBody RoleRequestDto request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Member member = authenticationService.getUserData(email);

        if(!authenticationService.checkUserRequest(member.getId()))
        {
            return Utils.getResponseEntity("Your previous request is already pending", HttpStatus.BAD_REQUEST);
        }

        if (authenticationService.roleRequest(request, email)) {
            return Utils.getResponseEntity("Request sent for role", HttpStatus.OK);
        } else {
            return Utils.getResponseEntity("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PatchMapping("/update-profile-pic")
    public ResponseEntity<String> updateProfilePic(MultipartFile photo) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Member member = authenticationService.getUserData(email);

        if(authenticationService.updateProfilePic(photo, member))
            return Utils.getResponseEntity("updated profile", HttpStatus.OK);
        else
            return Utils.getResponseEntity("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
