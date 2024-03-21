package com.example.relationshipJPA.Service;

import com.example.relationshipJPA.Dao.Resquest.Mail;

public interface sendmail {

    String sentMail(String subject, String body);

    void sendSimpleMessage(Mail mail);
}
