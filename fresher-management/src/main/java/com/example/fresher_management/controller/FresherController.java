package com.example.fresher_management.controller;

import com.example.fresher_management.entity.Fresher;
import com.example.fresher_management.service.FresherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/freshers")
@Slf4j
public class FresherController {
    @Autowired
    private FresherService fresherService;

    @PostMapping("")
    public ResponseEntity<Fresher> addFresher(@RequestBody Fresher fresher) {
        log.info("Received request to add fresher: {}", fresher);
        Fresher newFresher = fresherService.addFresher(fresher);
        log.info("Added new fresher with ID: {}", newFresher.getId());
        return ResponseEntity.ok(newFresher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fresher> updateFresher(@PathVariable int id, @RequestBody Fresher fresher) {
        log.info("Received request to update fresher with ID: {}", id);
        Fresher upFresher = fresherService.updateFresher(id, fresher);
        log.info("Updated fresher with ID: {}", id);
        return ResponseEntity.ok(upFresher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFresher(@PathVariable int id) {
        log.info("Received request to delete fresher with ID: {}", id);
        fresherService.deleteFresher(id);
        log.info("Deleted fresher with ID: {}", id);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("")
    public ResponseEntity<List<Fresher>> getFreshers(@RequestHeader("Authorization") String token) {
        log.info("Received request to get freshers");
        List<Fresher> freshers = fresherService.getFreshers(token);
        return ResponseEntity.ok(freshers);
    }

    @GetMapping("/search/name/{key}")
    public ResponseEntity<List<Fresher>> getSearchByName(@PathVariable String key, @RequestHeader("Authorization") String token) {
        log.info("Received request to search freshers by name: {}", key);
        List<Fresher> freshers = fresherService.getSearchByName(key, token);
        return ResponseEntity.ok(freshers);
    }

    @GetMapping("/search/email/{key}")
    public ResponseEntity<List<Fresher>> getSearchByEmail(@PathVariable String key, @RequestHeader("Authorization") String token) {
        log.info("Received request to search freshers by email: {}", key);
        List<Fresher> freshers = fresherService.getSearchByEmail(key, token);
        return ResponseEntity.ok(freshers);
    }

    @GetMapping("/search/language/{key}")
    public ResponseEntity<List<Fresher>> getSearchByLanguage(@PathVariable String key, @RequestHeader("Authorization") String token) {
        log.info("Received request to search freshers by language: {}", key);
        List<Fresher> freshers = fresherService.getSearchByLanguage(key, token);
        return ResponseEntity.ok(freshers);
    }

    @GetMapping("/scores/{id}")
    public ResponseEntity<Float> getScore(@PathVariable int id) {
        log.info("Received request to get score for fresher with ID: {}", id);
        Float score = fresherService.getScore(id);
        return ResponseEntity.ok(score);
    }
}
