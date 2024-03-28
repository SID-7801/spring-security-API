package com.example.relationshipJPA.Service;



import com.example.relationshipJPA.Dao.JwtAuthenticationResponse;
import com.example.relationshipJPA.Dao.Resquest.Signin;
import com.example.relationshipJPA.Dao.Resquest.Signup;
import com.example.relationshipJPA.Entity.Member;

public interface AuthenticationService {

    Boolean signup(Signup request);

    JwtAuthenticationResponse signin(Signin request);
    boolean updateProfile(Signup request, String username);
    Member getUserData(String username);
}

