package com.example.fresher_management.service;

import com.example.fresher_management.dto.StatCenterOutputDto;
import com.example.fresher_management.dto.StatFresherScoreRangeOutputDto;
import com.example.fresher_management.dto.StatisticInputDto;
import com.example.fresher_management.entity.User;

import java.util.List;

public interface StatisticService {
    public List<StatCenterOutputDto> statNumOfFresToCenter(StatisticInputDto statisticInputDto, User user);

    public List<StatFresherScoreRangeOutputDto> statFresherScoreRange(User user);
}
