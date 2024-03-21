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
import java.util.List;


@Service
@AllArgsConstructor
public class ComplainServiceImpl implements ComplainService {

    @Autowired
    private ComplainRepository complainRepository;

    @Autowired
    private MemberRepository memberRepository;


    @Override
    public String RaiseComplain(Complain request , String userName) {
        Member member = memberRepository.findByEmail(userName).get();

        Complain complain = new Complain();
        complain.setDescription(request.getDescription());
        complain.setStatus(Status.PROGRESS);
        complain.setMem_id(member);

        complainRepository.save(complain);
        return "successfully created";
    }

    @Override
    public List<Complain> getAllComplains() {
        List<Complain> complains = complainRepository.findAll();
        return complains;
    }


    @Override
    public List<Complain> getComplainByUserId(Long id) {
       List<Complain> complain = complainRepository.findByfk_Mem_Id(id);
        return complain;
    }


    public Complain completecomplain(Long id) {
        Complain complain = complainRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complain","id",id));
        complain.setStatus(Status.COMPLETED);
        Complain complain1 = complainRepository.save(complain);
        return complain1;
    }

}


