package com.example.relationshipJPA.Service.ServiceImpl;

import com.example.relationshipJPA.Entity.ContactUs;
import com.example.relationshipJPA.Repository.ContactUsRepository;
import com.example.relationshipJPA.Service.ContactUsService;
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
