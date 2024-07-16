package com.example.fresher_management.controller;

import com.example.fresher_management.entity.User;
import com.example.fresher_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userinfo")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        User user = userService.getUserByToken(jwt);
        return ResponseEntity.ok(user);
    }
}
