package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.Dao.Resquest.Mail;
import com.example.relationshipJPA.Service.sendmail;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/auth")
public class MailController {

    @Autowired
    private sendmail mail;

    @PostMapping("/send")
    public ResponseEntity<String> sendmail(@RequestBody Mail request ){
        mail.sentMail(request.getSubject(), request.getBody());
    return ResponseEntity.ok("hii");
    }
}
