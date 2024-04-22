package com.example.relationshipJPA.service;

import com.example.relationshipJPA.dao.JwtAuthenticationResponse;
import com.example.relationshipJPA.dao.resquest.RoleRequestDto;
import com.example.relationshipJPA.dao.resquest.Signin;
import com.example.relationshipJPA.dao.resquest.Signup;
import com.example.relationshipJPA.dao.resquest.UpdateProfileDto;
import com.example.relationshipJPA.entity.Member;
import com.example.relationshipJPA.entity.RoleRequest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AuthenticationService {

    Boolean signup(Signup request);
    JwtAuthenticationResponse signin(Signin request);
    boolean updateProfile(UpdateProfileDto request, String username);
    Member getUserData(String username);
    Boolean roleRequest(RoleRequestDto request, String email);
    Boolean checkUserRequest(Long id);
    Boolean updateProfilePic(MultipartFile photo, Member member) throws IOException;
}

