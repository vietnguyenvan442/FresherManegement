package com.example.fresher_management.controller;
import com.example.fresher_management.entity.*;
import com.example.fresher_management.service.FresherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/freshers")
public class FresherController {
    @Autowired
    private FresherService fresherService;

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<Fresher> addFresher(@RequestBody Fresher fresher) {
        Fresher newFresher = fresherService.addFresher(fresher);
        return ResponseEntity.ok(newFresher);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<Fresher> updateFresher(@PathVariable int id, @RequestBody Fresher fresher) {
        Fresher upFresher = fresherService.updateFresher(id, fresher);
        return ResponseEntity.ok(upFresher);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<String> deleteFresher(@PathVariable int id) {
        fresherService.deleteFresher(id);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<Fresher>> getFreshers(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(fresherService.getFreshers(token));
    }

    @GetMapping("search/name/{key}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<Fresher>> getSearchByName(@PathVariable String key, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(fresherService.getSearchByName(key, token));
    }

    @GetMapping("search/email/{key}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<Fresher>> getSearchByEmail(@PathVariable String key, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(fresherService.getSearchByEmail(key, token));
    }

    @GetMapping("search/language/{key}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<Fresher>> getSearchByLanguage(@PathVariable String key, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(fresherService.getSearchByLanguage(key, token));
    }

    @GetMapping("/scores/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public  ResponseEntity<Float> getScore(@PathVariable int id){
        return ResponseEntity.ok(fresherService.getScore(id));
    }
}
