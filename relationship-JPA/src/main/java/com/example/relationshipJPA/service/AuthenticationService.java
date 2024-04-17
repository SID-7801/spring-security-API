package com.example.relationshipJPA.service;



import com.example.relationshipJPA.dao.JwtAuthenticationResponse;
import com.example.relationshipJPA.dao.resquest.Signin;
import com.example.relationshipJPA.dao.resquest.Signup;
import com.example.relationshipJPA.dao.resquest.UpdateProfileDto;
import com.example.relationshipJPA.entity.Member;

public interface AuthenticationService {

    Boolean signup(Signup request);
    JwtAuthenticationResponse signin(Signin request);
    boolean updateProfile(UpdateProfileDto request, String username);
    Member getUserData(String username);

}

