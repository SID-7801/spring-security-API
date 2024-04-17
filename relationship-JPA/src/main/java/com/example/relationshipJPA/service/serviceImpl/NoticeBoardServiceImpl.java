package com.example.relationshipJPA.service.serviceImpl;

import com.example.relationshipJPA.entity.NoticeBoard;
import com.example.relationshipJPA.repository.NoticeBoardRepository;
import com.example.relationshipJPA.service.NoticeBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService {

    @Autowired
    private NoticeBoardRepository noticeBoardRepository;

    public Boolean createNotice(NoticeBoard request)
    {
        request.setCreateDate(LocalDate.now());
        noticeBoardRepository.save(request);
        return true;
    }

    public List<NoticeBoard> viewAll()
    {
        return noticeBoardRepository.findAll();
    }
}
