package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.Entity.ContactUs;
import com.example.relationshipJPA.Service.ContactUsService;
import com.example.relationshipJPA.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("lwresident/v1/contactus")
public class ContactUsController {

    @Autowired
    private ContactUsService contactUsService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitContact(@RequestBody ContactUs request)
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
