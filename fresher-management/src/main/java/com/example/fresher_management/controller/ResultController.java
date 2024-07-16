package com.example.fresher_management.controller;

import com.example.fresher_management.dto.ResultDto;
import com.example.fresher_management.entity.Result;
import com.example.fresher_management.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/results")
@CrossOrigin
public class ResultController {
    @Autowired
    private ResultService resultService;

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<Result> add(@RequestBody ResultDto resultDto, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(resultService.save(resultDto, token));
    }
}
