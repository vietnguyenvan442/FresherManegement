package com.example.fresher_management.controller;
import com.example.fresher_management.entity.*;
import com.example.fresher_management.service.FresherService;
import com.example.fresher_management.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping ("/fresher")
public class FresherController {
    @Autowired
    private FresherService fresherService;

    @Autowired
    private JwtUtil jwtUtil;

//    @GetMapping("/freshers")
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


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Fresher> addFresher(@RequestBody Fresher fresher) {
        Fresher newFresher = fresherService.addFresher(fresher);
        return ResponseEntity.ok(newFresher);
    }
}
