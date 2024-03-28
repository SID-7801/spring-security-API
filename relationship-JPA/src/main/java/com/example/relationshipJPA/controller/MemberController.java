package com.example.relationshipJPA.controller;


import com.example.relationshipJPA.Dao.Resquest.Signup;
import com.example.relationshipJPA.Entity.Member;
import com.example.relationshipJPA.Service.AuthenticationService;
import com.example.relationshipJPA.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lwresident/v1/member")
public class MemberController {

    @Autowired
    private AuthenticationService authenticationService;

    @PatchMapping("/updateProfile")
    public ResponseEntity<String> updateProfile(@RequestBody Signup request)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if(authenticationService.updateProfile(request, username))
        {
            return Utils.getResponseEntity("User updated successfully", HttpStatus.OK);
        }
        else
        {
            return Utils.getResponseEntity("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getUser")
    public Member getUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return authenticationService.getUserData(username);
    }
}
