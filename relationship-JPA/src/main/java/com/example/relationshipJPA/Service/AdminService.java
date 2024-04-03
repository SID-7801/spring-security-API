package com.example.relationshipJPA.Service;

import com.example.relationshipJPA.Dao.Resquest.MemberDto;
import com.example.relationshipJPA.Entity.Member;

import java.util.List;

public interface AdminService {
    List<Member> getMembers();
    Boolean deleteMember(Long id);
    List<Member> viewNotApprovedUsers();
    Boolean approveUser(Long id);
    Boolean declineUser(Long id);
}
