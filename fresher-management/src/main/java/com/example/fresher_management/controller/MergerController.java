package com.example.fresher_management.controller;

import com.example.fresher_management.dto.MergerDto;
import com.example.fresher_management.entity.Merger;
import com.example.fresher_management.service.MergerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mergers")
public class MergerController {
    @Autowired
    private MergerService mergerService;

    @PostMapping("")
    public ResponseEntity<Merger> addMerger(@RequestBody MergerDto mergerDto){
        Merger merger = mergerService.addMerger(mergerDto);
        return ResponseEntity.ok(merger);
    }
}
