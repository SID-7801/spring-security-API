package com.example.relationshipJPA.service;

import com.example.relationshipJPA.entity.Member;
import com.example.relationshipJPA.entity.RoleRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.List;

public interface AdminService {

    List<Member> getMembers();
    Boolean deleteMember(Long id);
    List<RoleRequest> viewNotApprovedUsers();
    Boolean approveUser(Long id, String userName);
    Boolean declineUser(Long id);

}
