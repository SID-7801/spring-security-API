package com.example.relationshipJPA.Service.ServiceImpl;

import com.example.relationshipJPA.Dao.Resquest.MemberDto;
import com.example.relationshipJPA.Entity.Member;
import com.example.relationshipJPA.Entity.Role;
import com.example.relationshipJPA.Entity.Status;
import com.example.relationshipJPA.Repository.MemberRepository;
import com.example.relationshipJPA.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Member> getMembers()
    {
        return memberRepository.findAll();
    }

    @Override
    public Boolean deleteMember(Long id)
    {
        memberRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Member> viewNotApprovedUsers()
    {
        return memberRepository.findUserByStatus();
    }
    @Override
    public Boolean approveUser(Long id)
    {
        Member member = memberRepository.findById(id).orElseThrow();
        member.setStatus(Status.APPROVED);
        memberRepository.save(member);
        return true;
    }

    @Override
    public Boolean declineUser(Long id)
    {
        Member member = memberRepository.findById(id).orElseThrow();
        member.setStatus(Status.APPROVED);
        member.setRole(Role.MEMBER);
        memberRepository.save(member);
        return true;
    }
}