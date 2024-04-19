package com.example.relationshipJPA.service.serviceImpl;

import com.example.relationshipJPA.dao.JwtAuthenticationResponse;
import com.example.relationshipJPA.dao.resquest.RoleRequestDto;
import com.example.relationshipJPA.dao.resquest.Signin;
import com.example.relationshipJPA.dao.resquest.Signup;
import com.example.relationshipJPA.dao.resquest.UpdateProfileDto;
import com.example.relationshipJPA.entity.Member;
import com.example.relationshipJPA.entity.Role;
import com.example.relationshipJPA.entity.RoleRequest;
import com.example.relationshipJPA.entity.Status;
import com.example.relationshipJPA.repository.MemberRepository;
import com.example.relationshipJPA.repository.RoleRequestRepository;
import com.example.relationshipJPA.service.AuthenticationService;
import com.example.relationshipJPA.service.JwtService;
import com.example.relationshipJPA.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private RoleRequestRepository roleRequestRepository;

    @Override
    public Boolean signup(Signup request) {
        if (checkUser(request.getEmail())) {
            if (request.getRole() == Role.MEMBER) {
                var member = Member.builder().name(request.getName())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .wing(request.getWing())
                        .flat(request.getFlat())
                        .mobile(request.getMobile())
                        .role(Role.MEMBER)
                        .status(Status.APPROVED)
                        .acCreateDate(LocalDate.now())
                        .build();
                memberRepository.save(member);
            }
            else{
                var member = Member.builder().name(request.getName())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .wing(request.getWing())
                        .flat(request.getFlat())
                        .mobile(request.getMobile())
                        .role(request.getRole())
                        .status(Status.NOT_APPROVED)
                        .acCreateDate(LocalDate.now())
                        .build();
                memberRepository.save(member);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public JwtAuthenticationResponse signin(Signin request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var member = memberRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Couldn't find'"));
        var jwt = jwtService.generateToken(member);

        return JwtAuthenticationResponse.builder().token(jwt).role(member.getRole()).userId(member.getId()).build();
    }

    public Boolean checkUser(String email) {
        var checkMember = memberRepository.findByEmail(email);
        return checkMember.isEmpty();
    }

    // get current logged in user details
    @Override
    public Member getUserData(String username) {
        return memberRepository.findByEmail(username).orElseThrow();
    }

    @Override
    public boolean updateProfile(UpdateProfileDto request, String username) {
        Member user = memberRepository.findByEmail(username).orElseThrow();
        user.setEmail(user.getEmail());
        user.setName(request.getName());
        user.setWing(request.getWing());
        user.setFlat(request.getFlat());
        user.setMobile(request.getMobile());

        memberRepository.save(user);
        return true;
    }

    @Override
    public Boolean roleRequest(RoleRequestDto request, String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow();

        RoleRequest roleRequest = new RoleRequest();

        roleRequest.setRequestedRole(request.getRequestedRole());
        roleRequest.setRequestDate(LocalDateTime.now());
        roleRequest.setApprovedDate(null);
        roleRequest.setApprovedBy(null);
        roleRequest.setStatus(Status.NOT_APPROVED);
        roleRequest.setMember(member);

//                .requestedRole(request.getRequestedRole())
//                .requestDate(LocalDateTime.now())
//                .approvedDate(null)
//                .status(Status.NOT_APPROVED)
//                .approvedBy(null)
//                .member(member)
//                .build();

        roleRequestRepository.save(roleRequest);
        return true;
    }

    // check user already submitted request
    public Boolean checkUserRequest(Long id) {
        RoleRequest query = roleRequestRepository.checkPendingRequestRole(id);
        return query == null;
    }
}