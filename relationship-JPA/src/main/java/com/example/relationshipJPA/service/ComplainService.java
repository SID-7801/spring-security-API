package com.example.relationshipJPA.service;

import com.example.relationshipJPA.entity.Complain;

import java.util.List;

public interface ComplainService {

    String raiseComplain(Complain request , String userName);

    List<Complain> getAllComplains();

    List<Complain> getComplainByUsername(String username);

    Complain completecomplain(Long id);
    Boolean deleteComplaint(Long compId);
}