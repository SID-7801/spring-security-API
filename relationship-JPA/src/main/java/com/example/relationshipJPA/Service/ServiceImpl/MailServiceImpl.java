package com.example.relationshipJPA.Service.ServiceImpl;

import com.example.relationshipJPA.Entity.Mail;
import com.example.relationshipJPA.Repository.MemberRepository;
import com.example.relationshipJPA.Service.sendmail;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MailServiceImpl implements sendmail {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public String sentMail(String subject, String body) {

        try{

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

            String[] allMemberEmails = memberRepository.findAllEmail();

            mimeMessageHelper.setTo(allMemberEmails);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);

            javaMailSender.send(mimeMessage);

            return "mail sent successfully";


        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
