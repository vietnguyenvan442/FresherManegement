package com.example.fresher_management.service.impl;

import com.example.fresher_management.dto.StatCenterInputDto;
import com.example.fresher_management.dto.StatCenterOutputDto;
import com.example.fresher_management.service.CenterService;
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

    @Override
    public List<StatCenterOutputDto> statNumOfFresToCenter(StatCenterInputDto statCenterInputDto) {
        log.info("Statistics on the number of freshers at the center during the period from " + statCenterInputDto.getStart_date() + " to " + statCenterInputDto.getEnd_date());
        return centerService.statNumOfFresToCenter(statCenterInputDto);
    }
}
