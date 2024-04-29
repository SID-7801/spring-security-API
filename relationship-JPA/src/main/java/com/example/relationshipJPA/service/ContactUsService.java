package com.example.relationshipJPA.service;

import com.example.relationshipJPA.dao.resquest.ContactUsDto;
import com.example.relationshipJPA.entity.ContactUs;

public interface ContactUsService {
    Boolean submitContact(ContactUsDto request);
}
