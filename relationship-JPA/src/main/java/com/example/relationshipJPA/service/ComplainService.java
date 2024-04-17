package com.example.relationshipJPA.service;

import com.example.relationshipJPA.dao.resquest.ComplainRequest;
import com.example.relationshipJPA.entity.Complain;

import java.io.IOException;
import java.util.List;

public interface ComplainService {

    String raiseComplain(ComplainRequest request , String userName) throws IOException;

    List<Complain> getAllComplains();

    List<Complain> getComplainByUsername(String username);

    Complain completecomplain(Long id);
    Boolean deleteComplaint(Long compId);

}