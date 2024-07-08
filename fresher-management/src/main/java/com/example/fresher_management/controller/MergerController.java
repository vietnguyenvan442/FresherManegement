package com.example.fresher_management.controller;

import com.example.fresher_management.dto.MergerDto;
<<<<<<< HEAD
=======
import com.example.fresher_management.dto.MergerFirstToSecondDto;
>>>>>>> 9f1e8361f333996e63834602545a5a7b55b43b6f
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
<<<<<<< HEAD
=======

    @PostMapping("/first-to-second")
    public ResponseEntity<Merger> addMergerFirstToSecond(@RequestBody MergerFirstToSecondDto mergerFirstToSecondDto){
        Merger merger = mergerService.addMergerFirstToSecond(mergerFirstToSecondDto);
        return ResponseEntity.ok(merger);
    }
>>>>>>> 9f1e8361f333996e63834602545a5a7b55b43b6f
}
