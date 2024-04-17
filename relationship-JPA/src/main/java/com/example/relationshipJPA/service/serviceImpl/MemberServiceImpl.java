package com.example.relationshipJPA.service.serviceImpl;


import com.example.relationshipJPA.repository.MemberRepository;
import com.example.relationshipJPA.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return memberRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("user not found"));
            }
        };
    }
}