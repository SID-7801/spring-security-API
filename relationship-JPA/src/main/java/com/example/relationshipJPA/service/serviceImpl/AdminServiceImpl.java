package com.example.relationshipJPA.service.serviceImpl;

import com.example.relationshipJPA.entity.Member;
import com.example.relationshipJPA.entity.Role;
import com.example.relationshipJPA.entity.RoleRequest;
import com.example.relationshipJPA.entity.Status;
import com.example.relationshipJPA.repository.MemberRepository;
import com.example.relationshipJPA.repository.RoleRequestRepository;
import com.example.relationshipJPA.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RoleRequestRepository roleRequestRepository;

    @Override
    public List<Member> getMembers()
    {
        return memberRepository.getAllMembersExceptAdmin();
    }

    @Override
    public Boolean deleteMember(Long id)
    {
        memberRepository.deleteById(id);
        return true;
    }

    @Override
    public List<RoleRequest> viewNotApprovedUsers()
    {
        return roleRequestRepository.findUserByStatus();
    }

    @Override
    public Boolean approveUser(Long id)
    {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String approvedUsername = authentication.getName();

        Member member = memberRepository.findById(id).orElseThrow();
        RoleRequest role = roleRequestRepository.checkPendingRequestRole(id);

        member.setStatus(Status.APPROVED);
        role.setStatus(Status.APPROVED);
        role.setApprovedBy(approvedUsername);
        role.setApprovedDate(LocalDateTime.now());

        roleRequestRepository.save(role);
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