package com.example.relationshipJPA.service;

import com.example.relationshipJPA.entity.Feedback;

import java.util.List;

public interface FeedbackService {

    String saveFeedback(Feedback feedback);

    List<Feedback> getAllFeedback();
}
