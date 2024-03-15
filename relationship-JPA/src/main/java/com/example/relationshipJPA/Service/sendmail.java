package com.example.relationshipJPA.Service;

import com.example.relationshipJPA.Entity.Mail;

import java.util.List;

public interface sendmail {

    String sentMail(String subject, String body);
}
