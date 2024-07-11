package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.Area;
import com.example.fresher_management.exception.ResourceNotFoundException;
import com.example.fresher_management.repository.AreaRepository;
import com.example.fresher_management.service.AreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "areas")
    public List<Area> getAllAreas() {
        log.info("Fetching all areas");
        List<Area> areas = areaRepository.findAll();
        log.info("Found {} areas", areas.size());
        return areas;
    }

    @Override
    @Transactional
    @CacheEvict(value = "areas", allEntries = true)
    public Area save(Area area) {
        log.info("Saving new area: {}", area);
        Area savedArea = areaRepository.save(area);
        log.info("Saved area with ID: {}", savedArea.getId());
        return savedArea;
    }

    @Override
    @Transactional
    @Cacheable(value = "area", key = "#id")
    public Area findById(int id) {
        log.info("Fetching area with ID: {}", id);
        return areaRepository.findById(id).orElseThrow(() -> {
            log.error("Area not found with ID: {}", id);
            return new ResourceNotFoundException("Area not found with id " + id);
        });
    }
}
