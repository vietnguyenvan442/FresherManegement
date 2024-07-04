package com.example.fresher_management.service;

import com.example.fresher_management.entity.Area;
import com.example.fresher_management.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AreaService {
    @Autowired
    private AreaRepository areaRepository;

    @Transactional
    public Area findById(int id){
        return areaRepository.findById(id).orElse(null);
    }

    @Transactional
    public Area save(Area area){
        return areaRepository.save(area);
    }
}
