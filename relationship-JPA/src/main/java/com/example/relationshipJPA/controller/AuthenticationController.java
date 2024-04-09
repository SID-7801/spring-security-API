package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.Dao.JwtAuthenticationResponse;
import com.example.relationshipJPA.Dao.Resquest.Signin;
import com.example.relationshipJPA.Dao.Resquest.Signup;
import com.example.relationshipJPA.Service.AuthenticationService;
import com.example.relationshipJPA.Service.JwtService;
import com.example.relationshipJPA.util.Utils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lwresident/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    
    @Autowired
    private JwtService jwtService;

    private UserDetails userDetails;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid Signup request) {
        if (authenticationService.signup(request))
            return Utils.getResponseEntity("Account created successfully", HttpStatus.OK);
        else
            return Utils.getResponseEntity("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody @Valid Signin request)
    {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

}

