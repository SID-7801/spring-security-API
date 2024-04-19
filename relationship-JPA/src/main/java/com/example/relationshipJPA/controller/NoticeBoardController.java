package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.entity.NoticeBoard;
import com.example.relationshipJPA.service.NoticeBoardService;
import com.example.relationshipJPA.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lwresident/v1/notice-board")
public class NoticeBoardController {

    @Autowired
    private NoticeBoardService noticeBoardService;

    @PostMapping("/create")
    public ResponseEntity<String> createNotice(@RequestBody NoticeBoard request)
    {
        noticeBoardService.createNotice(request);
        return Utils.getResponseEntity("Notice created!", HttpStatus.OK);
    }

    @GetMapping("/view")
    public List<NoticeBoard> viewAll()
    {
        return noticeBoardService.viewNoticeOfTheDay();
    }

    @GetMapping("/view-all")
    public List<NoticeBoard> viewCurrentDate()
    {
        return noticeBoardService.viewAll();
    }
}
