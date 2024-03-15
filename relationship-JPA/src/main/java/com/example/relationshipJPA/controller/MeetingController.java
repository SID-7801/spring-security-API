package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.Dao.Resquest.MeetingRequest;
import com.example.relationshipJPA.Entity.GuestUser;
import com.example.relationshipJPA.Entity.MeetingsAndagenda;
import com.example.relationshipJPA.Service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @PostMapping("/raiseMeeting")
    public ResponseEntity<String> raiseMeeting(@RequestBody MeetingRequest request){
        meetingService.raiseMeeting(request);
        return ResponseEntity.ok("Successfully Raised meeting");
    }

    @PatchMapping("/{id}/updateConclusion")
    public ResponseEntity<String> updateConclusion(@PathVariable("id") Long meetingid, @RequestBody MeetingRequest request){
        meetingService.updateConclusion(meetingid,request);
        return ResponseEntity.ok("Successfully updated");
    }

    @DeleteMapping("/{id}/deleteMeeting")
    public ResponseEntity<String> deleteMeeting(@PathVariable("id") Long meetingid){
        meetingService.deleteMeeting(meetingid);
        return ResponseEntity.ok("deleted successfully");
    }

    @GetMapping("/view")
    public ResponseEntity<List<MeetingsAndagenda>> viewMeeting(){
        List<MeetingsAndagenda> meetings = meetingService.viewMeeting();
        return ResponseEntity.ok(meetings);
    }

}
