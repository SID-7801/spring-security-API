package com.example.relationshipJPA.Repository;

import com.example.relationshipJPA.Entity.ForgetPassword;
import com.example.relationshipJPA.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ForgetPasswordRepository extends JpaRepository<ForgetPassword,Long> {

    @Query("select fp from ForgetPassword fp where fp.otp = ?1 and fp.member = ?2")
    Optional<ForgetPassword> findByOtpAndMember(Integer otp, Member member);
}
