package com.example.fresher_management.service.impl;

import com.example.fresher_management.dto.StatFresherScoreRangeOutputDto;
import com.example.fresher_management.dto.StatisticInputDto;
import com.example.fresher_management.dto.StatCenterOutputDto;
import com.example.fresher_management.service.CenterService;
import com.example.fresher_management.service.FresherService;
import com.example.fresher_management.service.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private CenterService centerService;

    @Autowired
    private FresherService fresherService;

    @Override
    public List<StatCenterOutputDto> statNumOfFresToCenter(StatisticInputDto statisticInputDto) {
        return centerService.statNumOfFresToCenter(statisticInputDto);
    }

    @Override
    public List<StatFresherScoreRangeOutputDto> statFresherScoreRange(StatisticInputDto statisticInputDto) {
        return fresherService.statFresherScoreRange(statisticInputDto);
    }
}
