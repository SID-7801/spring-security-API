package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.Dao.Resquest.ChangePassword;
import com.example.relationshipJPA.Dao.Resquest.Mail;
import com.example.relationshipJPA.Entity.ForgetPassword;
import com.example.relationshipJPA.Entity.Member;
import com.example.relationshipJPA.Repository.ForgetPasswordRepository;
import com.example.relationshipJPA.Repository.MemberRepository;
import com.example.relationshipJPA.Service.sendmail;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("lwresident/v1/forgetpassword")
public class ForgetPasswordController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private sendmail sendmail;

    @Autowired
    private ForgetPasswordRepository forgetPasswordRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpSession session;

    @PostMapping("/verifyMail")
    public ResponseEntity<String> verifyMail(@RequestParam String email){
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

        sendmail.sendSimpleMessage(mail);
        forgetPasswordRepository.save(fp);

        return ResponseEntity.ok("email sent");
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<String> verifyOtp(@RequestParam Integer otp){

        var email = (String) session.getAttribute("EMAIL");

        System.out.println(email);

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("please provide an valid email"));
        ForgetPassword fp = forgetPasswordRepository.findByOtpAndMember(otp,member).orElseThrow(() -> new RuntimeException("Invalid otp for email" + email));
        if(fp.getExpirationTime().before(Date.from(Instant.now()))){
            forgetPasswordRepository.deleteById(fp.getF_id());
            return new ResponseEntity<>("OTP has expired", HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok("OTp verified");
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePassword changePassword){

        var email = (String) session.getAttribute("EMAIL");

        if(!Objects.equals(changePassword.password(), changePassword.repeatPassword())){
            return new ResponseEntity<>("please enter the password again", HttpStatus.EXPECTATION_FAILED);
        }

        String encodedPassword = passwordEncoder.encode(changePassword.password());
        memberRepository.updatePassword(email,encodedPassword);
        return ResponseEntity.ok("Password has been updated successfully");
    }

    private Integer otpGenerator(){
        Random random = new Random();
        return random.nextInt(100000,999999);
    }
}
