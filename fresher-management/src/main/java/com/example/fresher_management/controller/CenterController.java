package com.example.fresher_management.controller;

<<<<<<< HEAD
import com.example.fresher_management.entity.*;
import com.example.fresher_management.service.CenterService;
=======
import com.example.fresher_management.dto.MergerDto;
import com.example.fresher_management.dto.RecordDto;
import com.example.fresher_management.entity.*;
import com.example.fresher_management.entity.Record;
import com.example.fresher_management.service.CenterService;
import com.example.fresher_management.service.RecordService;
import com.example.fresher_management.service.UserDetailsServiceImpl;
>>>>>>> 9f1e8361f333996e63834602545a5a7b55b43b6f
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

<<<<<<< HEAD
=======
    @Autowired
    private RecordService recordService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

>>>>>>> 9f1e8361f333996e63834602545a5a7b55b43b6f
    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Center>> getAllCenters() {
        List<Center> centers = centerService.getAllCenters();
        return ResponseEntity.ok(centers);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Center> addCenter(@RequestBody Center center) {
        Center newCenter = centerService.addCenter(center);
        return ResponseEntity.ok(newCenter);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Center> updateCenter(@PathVariable int id, @RequestBody Center center) {
        Center updatedCenter = centerService.updateCenter(id, center);
        return ResponseEntity.ok(updatedCenter);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCenter(@PathVariable int id) {
        centerService.deleteCenter(id);
        return ResponseEntity.ok("Success");
    }
}
