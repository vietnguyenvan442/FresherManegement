package com.example.fresher_management.controller;

import com.example.fresher_management.entity.*;
import com.example.fresher_management.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/centers")
public class CenterController {

    @Autowired
    private CenterService centerService;

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Center>> getAllCenters(@RequestHeader("Authorization") String token) {
        List<Center> centers = centerService.getAll(token);
        return ResponseEntity.ok(centers);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Center> addCenter(@RequestBody Center center) {
        Center newCenter = centerService.save(center);
        return ResponseEntity.ok(newCenter);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Center> updateCenter(@PathVariable int id, @RequestBody Center center) {
        Center updatedCenter = centerService.updateById(id, center);
        return ResponseEntity.ok(updatedCenter);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCenter(@PathVariable int id) {
        centerService.deleteById(id);
        return ResponseEntity.ok("Success");
    }
}
