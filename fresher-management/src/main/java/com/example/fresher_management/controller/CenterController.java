package com.example.fresher_management.controller;

import com.example.fresher_management.entity.Center;
import com.example.fresher_management.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/center")
public class CenterController {
    @Autowired
    private CenterService centerService;

    @PostMapping("/add")
    public ResponseEntity<Center> addCenter(@RequestBody Center center,
                                            @RequestParam int managerId,
                                            @RequestParam int areaId) {
        Center createdCenter = centerService.addCenter(center, managerId, areaId);
        return ResponseEntity.ok(createdCenter);
    }
}
