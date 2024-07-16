package com.example.fresher_management.controller;

import com.example.fresher_management.dto.MergerDto;
import com.example.fresher_management.entity.Merger;
import com.example.fresher_management.service.MergerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mergers")
@Slf4j
@CrossOrigin
public class MergerController {
    @Autowired
    private MergerService mergerService;

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Merger> addMerger(@RequestBody MergerDto mergerDto) {
        log.info("Received request to add merger: {}", mergerDto);
        Merger merger = mergerService.addMerger(mergerDto);
        log.info("Added new merger with ID: {}", merger.getId());
        return ResponseEntity.ok(merger);
    }
}
