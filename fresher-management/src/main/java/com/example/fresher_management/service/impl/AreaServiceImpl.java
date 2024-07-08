package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.Area;
import com.example.fresher_management.exception.ResourceNotFoundException;
import com.example.fresher_management.repository.AreaRepository;
import com.example.fresher_management.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaRepository areaRepository;

    @Override
    @Transactional
    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }

    @Override
    @Transactional
    public Area save(Area area) {
        return areaRepository.save(area);
    }

    @Override
    @Transactional
    public Area findById(int id) {
        return areaRepository.findById(id).orElse(null);
    }
}
