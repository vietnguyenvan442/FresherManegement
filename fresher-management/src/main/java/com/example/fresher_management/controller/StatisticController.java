package com.example.fresher_management.controller;

import com.example.fresher_management.dto.StatCenterOutputDto;
import com.example.fresher_management.dto.StatFresherScoreRangeOutputDto;
import com.example.fresher_management.dto.StatisticInputDto;
import com.example.fresher_management.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticController {
    @Autowired
    private StatisticService statisticService;

    @GetMapping("/centers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<StatCenterOutputDto>> statisticCenter(@RequestBody StatisticInputDto statisticInputDto){
        return ResponseEntity.ok(statisticService.statNumOfFresToCenter(statisticInputDto));
    }

    @GetMapping("/scores")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<StatFresherScoreRangeOutputDto>> statisticFresherScoreRange(){
        return ResponseEntity.ok(statisticService.statFresherScoreRange());
    }
}
