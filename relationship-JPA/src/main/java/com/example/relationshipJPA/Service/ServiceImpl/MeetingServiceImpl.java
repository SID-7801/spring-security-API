package com.example.relationshipJPA.Service.ServiceImpl;

import com.example.relationshipJPA.Dao.Resquest.MeetingRequest;
import com.example.relationshipJPA.Entity.MeetingsAndagenda;
import com.example.relationshipJPA.Exception.ResourceNotFoundException;
import com.example.relationshipJPA.Repository.MeetingRepository;
import com.example.relationshipJPA.Service.MeetingService;
import com.example.relationshipJPA.Service.sendmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private sendmail sendmail;
    @Override
    public MeetingsAndagenda raiseMeeting(MeetingRequest request) {
        MeetingsAndagenda meetingsAndagenda = new MeetingsAndagenda();
        meetingsAndagenda.setAgenda(request.getAgenda());
        meetingsAndagenda.setDate(request.getDate());
        meetingsAndagenda.setTime(request.getTime());
        meetingsAndagenda.setConclusion(null);

        sendmail.sentMail(meetingsAndagenda.getAgenda(), meetingsAndagenda.getDate() + " " + meetingsAndagenda.getTime());

        meetingRepository.save(meetingsAndagenda);
        return meetingsAndagenda;
    }

    @Override
    public MeetingsAndagenda updateConclusion(Long id, String request) {
        MeetingsAndagenda meetingsAndagenda = meetingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meeting","id",id));
        meetingsAndagenda.setConclusion(request);
        sendmail.sentMail("Final Conclusion of meeting : " + meetingsAndagenda.getAgenda(), "Conclusion : " + request);
        return meetingRepository.save(meetingsAndagenda);
    }

    @Override
    public void deleteMeeting(Long id) {
        meetingRepository.deleteById(id);
    }

    @Override
    public List<MeetingsAndagenda> viewMeeting() {
        return meetingRepository.findAll();
    }
}
