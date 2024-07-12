package com.example.fresher_management.controller;

import com.example.fresher_management.dto.RecordDto;
import com.example.fresher_management.entity.Record;
import com.example.fresher_management.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/records")
@Slf4j
public class RecordController {

    @Autowired
    private RecordService recordService;

    @PostMapping("/add-fresher-to-course")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Record> addRecord(@RequestBody RecordDto recordDto) {
        log.info("Received request to add record: {}", recordDto);
        Record savedRecord = recordService.addRecord(recordDto);
        log.info("Added new record with ID: {}", savedRecord.getId());
        return ResponseEntity.ok(savedRecord);
    }
}
