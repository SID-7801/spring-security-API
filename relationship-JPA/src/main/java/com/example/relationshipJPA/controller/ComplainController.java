package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.entity.Complain;
import com.example.relationshipJPA.service.ComplainService;
import com.example.relationshipJPA.util.Utils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("lwresident/v1/complaint")
public class ComplainController {

    @Autowired
    private ComplainService complainService;

    // register complaint for member, secretory, admin, guard, committee
    @PostMapping("/newComplaint")
    public ResponseEntity<String> raiseComplain(@RequestBody Complain request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        complainService.raiseComplain(request, username);
        return ResponseEntity.ok("complaint raised successfully");
    }

    // secretory, admin, committee view all complaint api
    @GetMapping("/view-all")
    public ResponseEntity<List<Complain>> getAllComplains() {
        List<Complain> complains = complainService.getAllComplains();
        return new ResponseEntity<>(complains, HttpStatus.OK);
    }

    // my complaint for all the users
    @GetMapping("/my-complaints")
    public ResponseEntity<List<Complain>> getComplains() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<Complain> complaints = complainService.getComplainByUsername(username);
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }

    // solve complaint api for secretory, admin
    @PatchMapping("/solve/{id}")
    public ResponseEntity<Complain> completeComplain(@PathVariable("id") Long id) {
        Complain complain = complainService.completecomplain(id);
        return new ResponseEntity<>(complain, HttpStatus.OK);
    }

    // delete api for secretory, admin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteComplaint(@PathVariable("id") Long compId) {
        if (complainService.deleteComplaint(compId)) {
            return Utils.getResponseEntity("Complaint deleted successfully", HttpStatus.OK);
        }
        return Utils.getResponseEntity("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}