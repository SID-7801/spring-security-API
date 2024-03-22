package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.Entity.Complain;
import com.example.relationshipJPA.Entity.Feedback;
import com.example.relationshipJPA.Service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/auth")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/feedback")
    public ResponseEntity<String> saveFeedback(@RequestBody Feedback feedback){
        feedbackService.saveFeedback(feedback);
        return ResponseEntity.ok("Feedback saved");
    }

    @GetMapping("/view-feedbacks")
    public ResponseEntity<List<Feedback>> getAllFeedback(){
        List<Feedback> feedbacks = feedbackService.getAllFeedback();
        return new ResponseEntity<>(feedbacks , HttpStatus.OK);
    }
}
