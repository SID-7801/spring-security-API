package com.example.relationshipJPA.Service.ServiceImpl;



import com.example.relationshipJPA.Dao.JwtAuthenticationResponse;
import com.example.relationshipJPA.Dao.Resquest.Signin;
import com.example.relationshipJPA.Dao.Resquest.Signup;
import com.example.relationshipJPA.Entity.Member;
import com.example.relationshipJPA.Entity.Role;
import com.example.relationshipJPA.Repository.MemberRepository;
import com.example.relationshipJPA.Service.AuthenticationService;
import com.example.relationshipJPA.Service.JwtService;
import com.example.relationshipJPA.Service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private RefreshTokenService refreshTokenService;


    @Override
    public JwtAuthenticationResponse signup(Signup request) {
        var member = Member.builder().name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .wing(request.getWing())
                .flat(request.getFlat())
                .mobile(request.getMobile())
                .role(Role.USER).build();
        memberRepository.save(member);
        var jwt = jwtService.generateToken(member);
        var refreshToken = refreshTokenService.createRefreshToken(member.getEmail());

        return JwtAuthenticationResponse.builder().token(jwt).refreshToken(refreshToken.getRefreshToken()).build();
    }

    @Override
    public JwtAuthenticationResponse signin(Signin request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),
                        request.getPassword()));

        var member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Couldn't find'"));

        var jwt = jwtService.generateToken(member);
        var refreshToken = refreshTokenService.createRefreshToken(member.getEmail());

        return JwtAuthenticationResponse.builder().token(jwt).refreshToken(refreshToken.getRefreshToken()).build();
    }
}

