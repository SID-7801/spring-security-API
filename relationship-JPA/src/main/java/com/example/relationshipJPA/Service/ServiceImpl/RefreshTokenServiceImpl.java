package com.example.relationshipJPA.Service.ServiceImpl;

import com.example.relationshipJPA.Entity.Member;
import com.example.relationshipJPA.Entity.RefreshToken;
import com.example.relationshipJPA.Repository.MemberRepository;
import com.example.relationshipJPA.Repository.RefreshTokenRepository;
import com.example.relationshipJPA.Service.RefreshTokenService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private MemberRepository memberRepository;
    @Override
    public RefreshToken createRefreshToken(String userName) {

        Member member = memberRepository.findByEmail(userName).get();
        RefreshToken refreshToken2 = member.getRefreshToken();

        if(refreshToken2 == null) {
                    refreshToken2 = RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expiry(Instant.now().plusMillis(20*60*60*1000))
                    .member(member)
                    .build();
        }else{
            refreshToken2.setExpiry(Instant.now().plusMillis(20*60*60*1000));
        }

        member.setRefreshToken(refreshToken2);



//        System.out.println(memberRepository.findByEmail(userName).get());
        refreshTokenRepository.save(refreshToken2);

        return refreshToken2;
    }

    @Override
    public RefreshToken verifyRefreshToken(String refreshToken) {

        RefreshToken refreshToken1 = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("refresh token not found"));

        if(refreshToken1.getExpiry().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken1);
            throw new RuntimeException("refresh token expired");
        }
        return refreshToken1;


    }
}
