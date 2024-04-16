package com.example.relationshipJPA.Service;

import com.example.relationshipJPA.Dao.Resquest.MeetingRequest;
import com.example.relationshipJPA.Entity.MeetingsAndagenda;

import java.util.List;

public interface MeetingService {

    MeetingsAndagenda raiseMeeting(MeetingRequest request);

    MeetingsAndagenda updateConclusion(Long id, String request);

    void deleteMeeting(Long id);

    List<MeetingsAndagenda> viewMeeting();
}
