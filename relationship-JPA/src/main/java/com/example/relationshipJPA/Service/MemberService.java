package com.example.relationshipJPA.Service;



import com.example.relationshipJPA.Dao.Resquest.Signup;
import com.example.relationshipJPA.Entity.Member;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService {
    UserDetailsService userDetailsService();
}

