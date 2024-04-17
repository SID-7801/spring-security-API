package com.example.relationshipJPA.service;

import com.example.relationshipJPA.entity.ContactUs;

public interface ContactUsService {
    Boolean submitContact(ContactUs request);
}
