package com.example.fresher_management.controller;

import com.example.fresher_management.dto.BearerToken;
import com.example.fresher_management.dto.LoginDto;
import com.example.fresher_management.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<BearerToken> createAuthenticationToken(@RequestBody LoginDto user){
        log.info("Received login request for user: {}", user.getUsername());
        BearerToken token = userService.generateToken(user);
        log.info("Generated token for user: {}", user.getUsername());
        return ResponseEntity.ok(token);
    }
}
