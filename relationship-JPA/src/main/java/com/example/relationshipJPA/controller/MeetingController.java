package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.dao.resquest.MeetingRequest;
import com.example.relationshipJPA.entity.MeetingsAndagenda;
import com.example.relationshipJPA.service.MeetingService;
import com.example.relationshipJPA.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lwresident/v1/meetings")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    // ADMIN & Secretory api for  create meeting
    @PostMapping("/create")
    public ResponseEntity<String> raiseMeeting(@RequestBody MeetingRequest request){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        meetingService.raiseMeeting(request, username);
        return Utils.getResponseEntity("Meeting successfully scheduled", HttpStatus.OK);
    }

    // ADMIN & Secretory api for  update conclusion of meeting
    @PatchMapping("/updateConclusion/{id}")
    public ResponseEntity<String> updateConclusion(@PathVariable("id") Long meetingid, @RequestParam("conclusion") String request){
        meetingService.updateConclusion(meetingid,request);
        return ResponseEntity.ok("Successfully updated");
    }

    // ADMIN & Secretory api for delete meeting
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMeeting(@PathVariable("id") Long meetingid)
    {
        meetingService.deleteMeeting(meetingid);
        return ResponseEntity.ok("deleted successfully");
    }

    // ADMIN & Secretory api for view meeting
    @GetMapping("/view-all")
    public ResponseEntity<List<MeetingsAndagenda>> viewMeeting(){
        List<MeetingsAndagenda> meetings = meetingService.viewMeeting();
        return ResponseEntity.ok(meetings);
    }

}