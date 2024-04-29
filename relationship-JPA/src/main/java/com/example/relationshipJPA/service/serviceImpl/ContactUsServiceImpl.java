package com.example.relationshipJPA.service.serviceImpl;

import com.example.relationshipJPA.dao.resquest.ContactUsDto;
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
    public Boolean submitContact(ContactUsDto request)
    {
        var newRequest = ContactUs.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .mobile(request.getMobile())
                .message(request.getMessage()).
                build();

        contactUsRepository.save(newRequest);
        return true;
    }
}
