package com.example.fresher_management.controller;
import com.example.fresher_management.entity.*;
import com.example.fresher_management.service.CenterService;
import com.example.fresher_management.service.FresherService;
import com.example.fresher_management.service.UserService;
import com.example.fresher_management.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping ("/fresher")
public class FresherController {
    @Autowired
    private FresherService fresherService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

//    @GetMapping("/list/all")
//    @PreAuthorize("hasRole('ADMIN', 'MANAGER')")
//    public List<Fresher> getFreshers() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User currentUser = (User) authentication.getPrincipal();
//
//        if (currentUser instanceof Admin) {
//            return fresherService.getAllFreshers();
//        } else if (currentUser instanceof Manager) {
//            Manager manager = (Manager) currentUser;
//            List<Integer> centerIds = new ArrayList<>();
//            manager.getListCenter().forEach(center -> centerIds.add(center.getId()));
//            return fresherService.getFreshersByCenterIds(centerIds);
//        } else {
//            throw new RuntimeException("Unauthorized access");
//        }
//    }

//    @GetMapping("/center/{centerId}")
//    @PreAuthorize("hasRole('ADMIN', 'MANAGER')")
//    public ResponseEntity<List<Fresher>> getFreshersByCenter(@PathVariable int centerId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User currentUser = userService.findByUsername(authentication.getName());
//
//        if (currentUser == null) {
//            return ResponseEntity.status(403).build();
//        }
//
//        if (currentUser.getPosition().getName().equals("ADMIN")) {
//            List<Fresher> freshers = fresherService.getFreshersByCenterId(centerId);
//            return ResponseEntity.ok(freshers);
//        } else if (currentUser.getPosition().getName().equals("MANAGER")) {
//            Manager manager = (Manager) currentUser;
//            boolean managesCenter = manager.getManagedCenters().stream()
//                    .anyMatch(center -> center.getId() == centerId);
//            if (managesCenter) {
//                List<Fresher> freshers = fresherService.getFreshersByCenterId(centerId);
//                return ResponseEntity.ok(freshers);
//            } else {
//                return ResponseEntity.status(403).build();
//            }
//        } else {
//            return ResponseEntity.status(403).build();
//        }
//    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN', 'MANAGER')")
    public ResponseEntity<Fresher> addFresher(@RequestBody Fresher fresher) {
        Fresher newFresher = fresherService.addFresher(fresher);
        return ResponseEntity.ok(newFresher);
    }


    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN', 'MANAGER')")
    public ResponseEntity<Fresher> updateFresher(@PathVariable int id, @RequestBody Fresher fresher) {
        Fresher upFresher = fresherService.updateFresher(id, fresher);
        return ResponseEntity.ok(upFresher);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN', 'MANAGER')")
    public ResponseEntity<String> deleteFresher(@PathVariable int id) {
        fresherService.deleteFresher(id);
        return ResponseEntity.ok("Success");
    }
}
