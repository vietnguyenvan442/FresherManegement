package com.example.fresher_management.controller;

import com.example.fresher_management.entity.Center;
import com.example.fresher_management.entity.User;
import com.example.fresher_management.service.CenterService;
import com.example.fresher_management.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/centers")
@Slf4j
@CrossOrigin
public class CenterController {

    @Autowired
    private CenterService centerService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<Center>> getAllCenters(@RequestHeader("Authorization") String token) {
        User user = userService.getUserByToken(token);
        log.info("Received request to get all centers with user: {}", user.getName());
        List<Center> centers = centerService.getAll(user);
        log.info("Returning {} centers", centers.size());
        return ResponseEntity.ok(centers);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Center> addCenter(@RequestBody Center center) {
        log.info("Received request to add a new center: {}", center);
        Center newCenter = centerService.save(center);
        log.info("Added new center: {}", newCenter);
        return ResponseEntity.ok(newCenter);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Center> updateCenter(@PathVariable int id, @RequestBody Center center) {
        log.info("Received request to update center with id: {}", id);
        Center updatedCenter = centerService.updateById(id, center);
        log.info("Updated center: {}", updatedCenter);
        return ResponseEntity.ok(updatedCenter);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCenter(@PathVariable int id) {
        log.info("Received request to delete center with id: {}", id);
        centerService.deleteById(id);
        log.info("Deleted center with id: {}", id);
        return ResponseEntity.ok("Success");
    }
}
