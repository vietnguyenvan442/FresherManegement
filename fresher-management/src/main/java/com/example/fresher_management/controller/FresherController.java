package com.example.fresher_management.controller;
import com.example.fresher_management.entity.*;
import com.example.fresher_management.service.FresherService;
<<<<<<< HEAD
=======
import com.example.fresher_management.service.UserDetailsServiceImpl;
import com.example.fresher_management.service.UserService;
>>>>>>> 9f1e8361f333996e63834602545a5a7b55b43b6f
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

<<<<<<< HEAD
=======
    @Autowired
    private UserService userService;

>>>>>>> 9f1e8361f333996e63834602545a5a7b55b43b6f
    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN', 'MANAGER')")
    public ResponseEntity<Fresher> addFresher(@RequestBody Fresher fresher) {
        Fresher newFresher = fresherService.addFresher(fresher);
        return ResponseEntity.ok(newFresher);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN', 'MANAGER')")
    public ResponseEntity<Fresher> updateFresher(@PathVariable int id, @RequestBody Fresher fresher) {
        Fresher upFresher = fresherService.updateFresher(id, fresher);
        return ResponseEntity.ok(upFresher);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN', 'MANAGER')")
    public ResponseEntity<String> deleteFresher(@PathVariable int id) {
        fresherService.deleteFresher(id);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("")
<<<<<<< HEAD
    @PreAuthorize("hasRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<Fresher>> getFreshers(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(fresherService.getFreshers(token));
=======
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Fresher>> getAllFreshers(){
        return ResponseEntity.ok(fresherService.getAllFreshers());
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<Fresher>> getFreshers(@RequestHeader("Authorization") String token){
        User user = userService.getUserByToken(token.substring(7));
        return ResponseEntity.ok(fresherService.getFreshers(user.getId()));
>>>>>>> 9f1e8361f333996e63834602545a5a7b55b43b6f
    }
}
