package com.example.fresher_management.controller;

import com.example.fresher_management.entity.Center;
import com.example.fresher_management.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/center")
public class CenterController {

    @Autowired
    private CenterService centerService;

    @PostMapping
    public ResponseEntity<Center> addCenter(@RequestBody Center center) {
        Center newCenter = centerService.addCenter(center);
        return ResponseEntity.ok(newCenter);
    }
}
