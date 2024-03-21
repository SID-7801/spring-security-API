package com.example.relationshipJPA.controller;


import com.example.relationshipJPA.Dao.JwtAuthenticationResponse;
import com.example.relationshipJPA.Dao.Resquest.Signin;
import com.example.relationshipJPA.Dao.Resquest.Signup;
import com.example.relationshipJPA.Entity.RefreshToken;
import com.example.relationshipJPA.Dao.Resquest.RefreshTokenRequest;
import com.example.relationshipJPA.Service.AuthenticationService;
import com.example.relationshipJPA.Service.JwtService;
import com.example.relationshipJPA.Service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lwresident/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private final AuthenticationService authenticationService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtService jwtService;
//    @Autowired
    private UserDetails userDetails;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody Signup request) {

        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody Signin request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

//    @PostMapping("/refresh")
//    public JwtAuthenticationResponse refreshJwtToken(@RequestBody RefreshTokenRequest request){
//        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(request.getRefreshToken());
//        refreshToken.getMember();
//        String token = this.jwtService.generateToken(userDetails);
//        return  JwtAuthenticationResponse.builder().refreshToken(refreshToken.getRefreshToken())
//                .token(token)
//                .build();
//    }



}

