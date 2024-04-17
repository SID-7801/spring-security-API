package com.example.relationshipJPA.service.serviceImpl;

import com.example.relationshipJPA.dao.resquest.Mail;
import com.example.relationshipJPA.repository.MemberRepository;
import com.example.relationshipJPA.service.sendmail;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;



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

    public void sendSimpleMessage(Mail mail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getSendto());
        message.setFrom(from);
        message.setSubject(mail.getSubject());
        message.setText(mail.getBody());

        javaMailSender.send(message);
    }


}
