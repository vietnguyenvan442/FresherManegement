package com.example.fresher_management.service;

import com.example.fresher_management.dto.StatisticInputDto;
import com.example.fresher_management.dto.StatCenterOutputDto;
import com.example.fresher_management.entity.Center;
import java.util.List;

public interface CenterService {
    public List<Center> getAll(String token);
    public Center save(Center center);
    public Center updateById(int id, Center updatedCenter);
    public void deleteById(int id);
    public Center findById(int id);
    public List<StatCenterOutputDto> statNumOfFresToCenter(StatisticInputDto statisticInputDto);
}
