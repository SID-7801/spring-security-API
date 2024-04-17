package com.example.relationshipJPA.controller;

import com.example.relationshipJPA.entity.Maintenance;
import com.example.relationshipJPA.service.MaintenanceService;
import com.example.relationshipJPA.util.Utils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("lwresident/v1/maintenance")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateMaintenance()
    {
        maintenanceService.generateMaintenance();
        return Utils.getResponseEntity("generated!", HttpStatus.OK);
    }

    @GetMapping("/my-maintenance")
    public List<Maintenance> myMaintenance()
    {
        return null;
    }
}
