package com.example.relationshipJPA.Service;

import com.example.relationshipJPA.Entity.Complain;

import java.util.List;
import java.util.Optional;

public interface ComplainService {

    String raiseComplain(Complain request , String userName);

    List<Complain> getAllComplains();

    List<Complain> getComplainByUsername(String username);

    Complain completecomplain(Long id);
    Boolean deleteComplaint(Long compId);
}