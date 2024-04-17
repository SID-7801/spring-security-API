package com.example.relationshipJPA.service;

import com.example.relationshipJPA.dao.resquest.Mail;

public interface sendmail {

    String sentMail(String subject, String body);

    void sendSimpleMessage(Mail mail);
}
