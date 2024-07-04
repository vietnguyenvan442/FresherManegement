package com.example.fresher_management.controller;

import com.example.fresher_management.dto.BearerToken;
import com.example.fresher_management.dto.LoginDto;
import com.example.fresher_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<BearerToken> createAuthenticationToken(@RequestBody LoginDto user){
        return ResponseEntity.ok(userService.generateToken(user));
    }
}
