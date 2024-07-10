package com.example.fresher_management.controller;

import com.example.fresher_management.dto.StatCenterInputDto;
import com.example.fresher_management.dto.StatCenterOutputDto;
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
@RequestMapping("/statistic")
public class StatisticController {
    @Autowired
    private StatisticService statisticService;

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<StatCenterOutputDto>> statisticCenter(@RequestBody StatCenterInputDto statCenterInputDto){
        return ResponseEntity.ok(statisticService.statNumOfFresToCenter(statCenterInputDto));
    }
}
