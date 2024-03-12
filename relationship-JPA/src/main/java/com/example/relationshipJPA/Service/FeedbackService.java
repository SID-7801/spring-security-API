package com.example.relationshipJPA.Service;

import com.example.relationshipJPA.Entity.Feedback;

import java.util.List;

public interface FeedbackService {

    String saveFeedback(Feedback feedback);

    List<Feedback> getAllFeedback();
}
