package com.example.relationshipJPA.Service.ServiceImpl;

import com.example.relationshipJPA.Entity.Feedback;
import com.example.relationshipJPA.Repository.FeedbackRepository;
import com.example.relationshipJPA.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
   private FeedbackRepository feedbackRepository;
    @Override
    public String saveFeedback(Feedback feedback) {
//        System.out.println(feedback);
        feedbackRepository.save(feedback);
        return "successfully saved";
    }

    @Override
    public List<Feedback> getAllFeedback() {
        List<Feedback> feedbackList = feedbackRepository.findAll();
        return feedbackList;
    }
}
