package com.example.fresher_management.controller;

import com.example.fresher_management.dto.StatCenterOutputDto;
import com.example.fresher_management.dto.StatFresherScoreRangeOutputDto;
import com.example.fresher_management.dto.StatisticInputDto;
import com.example.fresher_management.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@CrossOrigin
public class StatisticController {
    @Autowired
    private StatisticService statisticService;

    @GetMapping("/centers")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<StatCenterOutputDto>> statisticCenter(@RequestBody StatisticInputDto statisticInputDto, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(statisticService.statNumOfFresToCenter(statisticInputDto, token));
    }

    @GetMapping("/scores")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<List<StatFresherScoreRangeOutputDto>> statisticFresherScoreRange(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(statisticService.statFresherScoreRange(token));
    }
}
