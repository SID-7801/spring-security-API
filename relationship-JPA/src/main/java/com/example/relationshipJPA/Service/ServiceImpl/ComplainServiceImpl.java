package com.example.relationshipJPA.Service.ServiceImpl;

import com.example.relationshipJPA.Entity.Complain;
import com.example.relationshipJPA.Entity.Member;
import com.example.relationshipJPA.Entity.Status;
import com.example.relationshipJPA.Exception.ResourceNotFoundException;
import com.example.relationshipJPA.Repository.ComplainRepository;
import com.example.relationshipJPA.Repository.MemberRepository;
import com.example.relationshipJPA.Service.ComplainService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public String raiseComplain(Complain request , String userName) {
        Member member = memberRepository.findByEmail(userName).get();

        Complain complain = new Complain();
        complain.setDescription(request.getDescription());
        complain.setTitle(request.getTitle());
        complain.setComplaintDate(LocalDateTime.now());
        complain.setStatus(Status.PROGRESS);
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


