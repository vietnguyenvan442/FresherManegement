package com.example.fresher_management.controller;

import com.example.fresher_management.entity.Center;
import com.example.fresher_management.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/center")
public class CenterController {

    @Autowired
    private CenterService centerService;

    @GetMapping("/list")
    public ResponseEntity<List<Center>> getAllCenters() {
        List<Center> centers = centerService.getAllCenters();
        return ResponseEntity.ok(centers);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Center> addCenter(@RequestBody Center center) {
        Center newCenter = centerService.addCenter(center);
        return ResponseEntity.ok(newCenter);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Center> updateCenter(@PathVariable int id, @RequestBody Center center) {
        Center updatedCenter = centerService.updateCenter(id, center);
        return ResponseEntity.ok(updatedCenter);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCenter(@PathVariable int id) {
        centerService.deleteCenter(id);
        return ResponseEntity.ok("Success");
    }
}
