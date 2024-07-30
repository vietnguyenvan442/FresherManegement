package com.example.fresher_management.service;

import com.example.fresher_management.dto.StatCenterOutputDto;
import com.example.fresher_management.dto.StatisticInputDto;
import com.example.fresher_management.entity.Center;
import com.example.fresher_management.entity.User;

import java.util.List;

public interface CenterService {
    public List<Center> getAll(User user);

    public Center save(Center center);

    public Center updateById(int id, Center updatedCenter);

    public void deleteById(int id);

    public Center findById(int id);

    public List<StatCenterOutputDto> statNumOfFresToCenter(StatisticInputDto statisticInputDto);

    public List<StatCenterOutputDto> statNumOfFresToCenterForManager(StatisticInputDto statisticInputDto, int manager_id);
}
