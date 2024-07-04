package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.Manager;
import com.example.fresher_management.exception.ResourceNotFoundException;
import com.example.fresher_management.repository.ManagerRepository;
import com.example.fresher_management.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Transactional
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    @Transactional
    public Manager save(Manager manager) {
        return managerRepository.save(manager);
    }

    @Transactional
    public Manager findById(int id) {
        return managerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Manager not found with id " + id));
    }
}
