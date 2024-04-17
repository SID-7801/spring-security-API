package com.example.relationshipJPA.service;

import com.example.relationshipJPA.entity.NoticeBoard;

import java.util.List;

public interface NoticeBoardService {

    Boolean createNotice(NoticeBoard request);
    List<NoticeBoard> viewAll();

}
