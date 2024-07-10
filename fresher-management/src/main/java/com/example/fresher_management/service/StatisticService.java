package com.example.fresher_management.service;

import com.example.fresher_management.dto.StatCenterInputDto;
import com.example.fresher_management.dto.StatCenterOutputDto;

import java.util.List;

public interface StatisticService {
    public List<StatCenterOutputDto> statNumOfFresToCenter(StatCenterInputDto statCenterInputDto);
}
