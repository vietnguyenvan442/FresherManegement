package com.example.fresher_management.controller;

import com.example.fresher_management.entity.User;
import com.example.fresher_management.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        log.info("Getting info user");
        User user = userService.getUserByToken(jwt);
        log.info("Getted info user: {}", user.getName());
        return ResponseEntity.ok(user);
    }
}
