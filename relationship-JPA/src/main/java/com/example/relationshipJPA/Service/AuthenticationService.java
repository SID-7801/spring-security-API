package com.example.relationshipJPA.Service;



import com.example.relationshipJPA.Dao.JwtAuthenticationResponse;
import com.example.relationshipJPA.Dao.Resquest.Signin;
import com.example.relationshipJPA.Dao.Resquest.Signup;

public interface AuthenticationService {

    JwtAuthenticationResponse signup(Signup request);

    JwtAuthenticationResponse signin(Signin request);


}

