package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.dao.resquest.ContactUsDto;
import com.example.relationshipJPA.entity.ContactUs;
import com.example.relationshipJPA.service.ContactUsService;
import com.example.relationshipJPA.util.Utils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lwresident/v1/contactus")
public class ContactUsController {

    @Autowired
    private ContactUsService contactUsService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitContact(@Valid @RequestBody ContactUsDto request)
    {
        if(contactUsService.submitContact(request))
        {
            return Utils.getResponseEntity("Submitted successfully!", HttpStatus.OK);
        }
        else
        {
            return Utils.getResponseEntity("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}