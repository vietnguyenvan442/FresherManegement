package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.Manager;
import com.example.fresher_management.exception.ResourceNotFoundException;
import com.example.fresher_management.repository.ManagerRepository;
import com.example.fresher_management.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    @Transactional
    @Cacheable(value = "managers")
    public List<Manager> getAllManagers() {
        log.info("Fetching all managers");
        return managerRepository.findAll();
    }

    @Override
    @Transactional
    @CacheEvict(value = "managers", allEntries = true)
    public Manager save(Manager manager) {
        log.info("Saving manager: {}", manager);
        return managerRepository.save(manager);
    }

    @Override
    @Transactional
    @Cacheable(value = "managers", key = "#id")
    public Manager findById(int id) {
        log.info("Fetching manager by id: {}", id);
        return managerRepository.findById(id).orElse(null);
    }
}
