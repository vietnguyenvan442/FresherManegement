package com.example.fresher_management.service.impl;

import com.example.fresher_management.dto.StatCenterOutputDto;
import com.example.fresher_management.dto.StatFresherScoreRangeOutputDto;
import com.example.fresher_management.dto.StatisticInputDto;
import com.example.fresher_management.entity.User;
import com.example.fresher_management.exception.ForbiddenException;
import com.example.fresher_management.service.CenterService;
import com.example.fresher_management.service.FresherService;
import com.example.fresher_management.service.StatisticService;
import com.example.fresher_management.service.UserService;
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

    @Autowired
    private UserService userService;

    @Override
    public List<StatCenterOutputDto> statNumOfFresToCenter(StatisticInputDto statisticInputDto, User user) {
        if ("ADMIN".equalsIgnoreCase(user.getRole().getName())) {
            return centerService.statNumOfFresToCenter(statisticInputDto);
        } else if ("MANAGER".equalsIgnoreCase(user.getRole().getName())) {
            return centerService.statNumOfFresToCenterForManager(statisticInputDto, user.getId());
        }
        log.error("Forbidden");
        throw new ForbiddenException("Forbidden");
    }

    @Override
    public List<StatFresherScoreRangeOutputDto> statFresherScoreRange(User user) {
        return fresherService.getFresherScoreRangeStats(user);
    }
}
