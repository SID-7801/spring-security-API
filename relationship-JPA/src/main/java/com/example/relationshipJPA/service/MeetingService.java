package com.example.relationshipJPA.service;

import com.example.relationshipJPA.dao.resquest.MeetingRequest;
import com.example.relationshipJPA.entity.MeetingsAndagenda;

import java.util.List;

public interface MeetingService {

    MeetingsAndagenda raiseMeeting(MeetingRequest request, String username);

    MeetingsAndagenda updateConclusion(Long id, String request);

    void deleteMeeting(Long id);

    List<MeetingsAndagenda> viewMeeting();
}
