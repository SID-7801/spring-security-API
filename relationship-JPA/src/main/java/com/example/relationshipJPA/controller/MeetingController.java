package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.Dao.Resquest.MeetingRequest;
import com.example.relationshipJPA.Entity.GuestUser;
import com.example.relationshipJPA.Entity.MeetingsAndagenda;
import com.example.relationshipJPA.Service.MeetingService;
import com.example.relationshipJPA.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("lwresident/v1/meetings")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    // ADMIN & Secretory api for  create meeting
    @PostMapping("/raiseMeeting")
    public ResponseEntity<String> raiseMeeting(@RequestBody MeetingRequest request){
        meetingService.raiseMeeting(request);
        return Utils.getResponseEntity("Meeting successfully scheduled", HttpStatus.OK);
    }

    // ADMIN & Secretory api for  update conclusion of meeting
    @PatchMapping("/updateConclusion/{id}")
    public ResponseEntity<String> updateConclusion(@PathVariable("id") Long meetingid, @RequestBody MeetingRequest request){
        meetingService.updateConclusion(meetingid,request);
        return ResponseEntity.ok("Successfully updated");
    }

    // ADMIN & Secretory api for delete meeting
    @DeleteMapping("/deleteMeeting/{id}")
    public ResponseEntity<String> deleteMeeting(@PathVariable("id") Long meetingid)
    {
        meetingService.deleteMeeting(meetingid);
        return ResponseEntity.ok("deleted successfully");
    }

    // ADMIN & Secretory api for view meeting
    @GetMapping("/view")
    public ResponseEntity<List<MeetingsAndagenda>> viewMeeting(){
        List<MeetingsAndagenda> meetings = meetingService.viewMeeting();
        return ResponseEntity.ok(meetings);
    }

}
