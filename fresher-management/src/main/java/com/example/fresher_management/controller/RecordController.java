package com.example.fresher_management.controller;

import com.example.fresher_management.dto.RecordDto;
import com.example.fresher_management.entity.Record;
import com.example.fresher_management.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("records")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Record> addRecord(@RequestBody RecordDto recordDto) {
        Record savedRecord = recordService.addRecord(recordDto);
        return ResponseEntity.ok(savedRecord);
    }
}
