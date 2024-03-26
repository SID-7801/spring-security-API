package com.example.relationshipJPA.Service;

import com.example.relationshipJPA.Entity.Complain;

import java.util.List;
import java.util.Optional;

public interface ComplainService {

    String RaiseComplain(Complain request , String userName);

    List<Complain> getAllComplains();

    List<Complain> getComplainByUserId(Long mem_id);

    Complain completecomplain(Long id);
    Boolean deleteComplaint(Long compId);
}