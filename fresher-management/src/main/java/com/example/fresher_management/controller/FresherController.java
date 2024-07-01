package com.example.fresher_management.controller;
import com.example.fresher_management.dto.FresherDto;
import com.example.fresher_management.entity.Admin;
import com.example.fresher_management.entity.Fresher;
import com.example.fresher_management.entity.Manager;
import com.example.fresher_management.entity.User;
import com.example.fresher_management.service.FresherService;
import com.example.fresher_management.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Fresher addFresher(@RequestBody FresherDto fresherDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Kiểm tra nếu người dùng hiện tại là Admin hoặc Manager
        if (userDetails.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN") || role.getAuthority().equals("ROLE_MANAGER"))) {
            return fresherService.addFresher(fresherDto);
        } else {
            throw new RuntimeException("Unauthorized access");
        }
    }
}
