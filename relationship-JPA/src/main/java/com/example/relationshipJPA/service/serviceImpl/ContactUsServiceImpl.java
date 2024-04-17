package com.example.relationshipJPA.service.serviceImpl;

import com.example.relationshipJPA.entity.ContactUs;
import com.example.relationshipJPA.repository.ContactUsRepository;
import com.example.relationshipJPA.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactUsServiceImpl implements ContactUsService {

    @Autowired
    private ContactUsRepository contactUsRepository;

    @Override
    public Boolean submitContact(ContactUs request)
    {
        contactUsRepository.save(request);
        System.out.println(request);
        return true;
    }
}
