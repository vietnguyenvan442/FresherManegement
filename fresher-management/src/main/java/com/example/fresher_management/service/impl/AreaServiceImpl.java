package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.Area;
import com.example.fresher_management.exception.AlreadyExistsException;
import com.example.fresher_management.repository.AreaRepository;
import com.example.fresher_management.service.AreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaRepository areaRepository;

    @Override
    @Transactional
    public List<Area> getAllAreas() {
        log.info("Fetching all areas");
        List<Area> areas = areaRepository.findAll();
        log.info("Found {} areas", areas.size());
        return areas;
    }

    @Override
    @Transactional
    public Area save(Area area) {
        log.info("Saving new area: {}", area);
        if (areaRepository.findByName(area.getName()) != null) throw new AlreadyExistsException("Area already exists");
        Area savedArea = areaRepository.save(area);
        log.info("Saved area with ID: {}", savedArea.getId());
        return savedArea;
    }

    @Override
    @Transactional
    public Area findById(int id) {
        log.info("Fetching area with ID: {}", id);
        return areaRepository.findById(id).orElse(null);
    }
}
