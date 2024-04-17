package com.example.relationshipJPA.service;

import com.example.relationshipJPA.entity.Member;

import java.util.List;

public interface AdminService {

    List<Member> getMembers();
    Boolean deleteMember(Long id);
    List<Member> viewNotApprovedUsers();
    Boolean approveUser(Long id);
    Boolean declineUser(Long id);

}
