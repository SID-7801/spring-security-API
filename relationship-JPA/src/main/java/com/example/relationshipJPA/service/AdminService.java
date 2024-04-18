package com.example.relationshipJPA.service;

import com.example.relationshipJPA.entity.Member;
import com.example.relationshipJPA.entity.RoleRequest;

import java.util.List;

public interface AdminService {

    List<Member> getMembers();
    Boolean deleteMember(Long id);
    List<RoleRequest> viewNotApprovedUsers();
    Boolean approveUser(Long id);
    Boolean declineUser(Long id);

}
