package com.example.relationshipJPA.service.serviceImpl;

import com.example.relationshipJPA.entity.Complain;
import com.example.relationshipJPA.dao.resquest.ComplainRequest;
import com.example.relationshipJPA.entity.Member;
import com.example.relationshipJPA.entity.Status;
import com.example.relationshipJPA.exception.ResourceNotFoundException;
import com.example.relationshipJPA.repository.ComplainRepository;
import com.example.relationshipJPA.repository.MemberRepository;
import com.example.relationshipJPA.service.ComplainService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class ComplainServiceImpl implements ComplainService {

    @Autowired
    private ComplainRepository complainRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public String raiseComplain(@Valid ComplainRequest request , String userName) throws IOException {

        Member member = memberRepository.findByEmail(userName).get();
        byte[] bytes = request.getPhoto().getBytes();

        Complain complain = new Complain();
        complain.setDescription(request.getDescription());
        complain.setTitle(request.getTitle());
        complain.setComplaintDate(LocalDateTime.now());
        complain.setStatus(Status.PROGRESS);
        complain.setPhoto(bytes);
        complain.setMem_id(member);

        complainRepository.save(complain);
        return "successfully created";
    }

    @Override
    public List<Complain> getAllComplains() {
        return complainRepository.findAll();
    }


    @Override
    public List<Complain> getComplainByUsername(String username) {

        Member member = memberRepository.findByEmail(username).orElseThrow();
        return complainRepository.findByfk_Mem_Id(member.getId());
    }


    public Complain completecomplain(Long id) {
        Complain complain = complainRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complain","id",id));
        complain.setStatus(Status.COMPLETED);

        return complainRepository.save(complain);
    }

    @Override
    public Boolean deleteComplaint(Long compId) {
        complainRepository.findById(compId).orElseThrow(() -> new RuntimeException("complaint is not exists with complaint id :" + compId));
        complainRepository.deleteById(compId);
        return true;
    }
}


