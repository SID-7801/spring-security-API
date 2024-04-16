package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.Dao.Resquest.ChangePassword;
import com.example.relationshipJPA.Dao.Resquest.Mail;
import com.example.relationshipJPA.Entity.ForgetPassword;
import com.example.relationshipJPA.Entity.Member;
import com.example.relationshipJPA.Repository.ForgetPasswordRepository;
import com.example.relationshipJPA.Repository.MemberRepository;
import com.example.relationshipJPA.Service.sendmail;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@RestController
@Slf4j
@RequestMapping("lwresident/v1/forgetPassword")
public class ForgetPasswordController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private sendmail sendmail;

    @Autowired
    private ForgetPasswordRepository forgetPasswordRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/verifyMail")
    public ResponseEntity<String> verifyMail(@RequestParam String email, HttpSession session){
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("please provide an valid email"));

        session.setAttribute("EMAIL", email);

        int otp = otpGenerator();
        Mail mail = Mail.builder()
                .sendto(email)
                .body("This is the otp for your Forget password :" + otp)
                .subject("OTP for Forgot password")
                .build();
        ForgetPassword fp = ForgetPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 5 * 60*1000))
                .member(member)
                .build();

        System.out.println("OTP =====> " + otp);
//        System.out.println("EMAIL ======> " + email);
        forgetPasswordRepository.save(fp);

        return ResponseEntity.ok("email sent : " + otp);
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<String> verifyOtp(@RequestParam Integer otp){

        Optional<ForgetPassword> record = forgetPasswordRepository.findByOtp(otp);
        if (record.isEmpty()) {
            return new ResponseEntity<>("Invalid OTP", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        ForgetPassword fp = record.get();

        if(fp.getExpirationTime().before(Date.from(Instant.now()))){

            // Remove used OTP entry for security
            forgetPasswordRepository.deleteById(fp.getF_id());

            return new ResponseEntity<>("OTP has expired", HttpStatus.EXPECTATION_FAILED);
        }

        return ResponseEntity.ok("OTP verified");
    }

    @PatchMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePassword changePassword, HttpSession session){
        if(!Objects.equals(changePassword.password(), changePassword.cPassword())){
            return new ResponseEntity<>("please enter the password again", HttpStatus.EXPECTATION_FAILED);
        }

        String email = (String) session.getAttribute("EMAIL");

        String encodedPassword = passwordEncoder.encode(changePassword.password());
        memberRepository.updatePassword(email,encodedPassword);
        return ResponseEntity.ok("Password has been updated successfully");
    }

    private Integer otpGenerator(){
        Random random = new Random();
        return random.nextInt(100000,999999);
    }
}
