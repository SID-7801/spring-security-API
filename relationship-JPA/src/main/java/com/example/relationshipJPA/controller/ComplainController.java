package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.Entity.Complain;
import com.example.relationshipJPA.Service.ComplainService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/auth")
public class ComplainController {

    @Autowired
    private ComplainService complainService;

    @PostMapping("/raise")
    public ResponseEntity<String> raisecomplain(@RequestBody Complain request)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        complainService.RaiseComplain(request ,username);
        return ResponseEntity.ok("complaint raised successfully");
    }

    @GetMapping("/view-complains")
    public ResponseEntity<List<Complain>> getAllComplains(){
        List<Complain> complains = complainService.getAllComplains();
        return new ResponseEntity<>(complains , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Complain>> getComplains(@PathVariable("id") Long id) {
        List<Complain> complain = complainService.getComplainByUserId(id);
        return new ResponseEntity<>(complain , HttpStatus.OK);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Complain> completeComplain(@PathVariable("id") Long id){
        Complain complain = complainService.completecomplain(id);
        return new ResponseEntity<>(complain, HttpStatus.OK);
    }

}



