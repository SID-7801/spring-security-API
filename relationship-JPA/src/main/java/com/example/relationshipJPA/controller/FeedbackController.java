package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.Entity.Complain;
import com.example.relationshipJPA.Entity.Feedback;
import com.example.relationshipJPA.Service.FeedbackService;
import com.example.relationshipJPA.util.Utils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("lwresident/v1/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // submit api for all the users
    @PostMapping("/new")
    public ResponseEntity<String> saveFeedback(@RequestBody Feedback feedback) {
        feedbackService.saveFeedback(feedback);
        return Utils.getResponseEntity("Feedback submitted", HttpStatus.OK);
    }

    // view api for admin, secretory
    @GetMapping("/view")
    public List<Feedback> getAllFeedback() {
        return feedbackService.getAllFeedback();
    }
}
