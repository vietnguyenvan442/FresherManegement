package com.example.fresher_management.service;

import com.example.fresher_management.entity.Manager;
import com.example.fresher_management.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Transactional
    public Manager findById(int id){
        return managerRepository.findById(id).orElse(null);
    }

    @Transactional
    public Manager save(Manager manager){
        return managerRepository.save(manager);
    }
}
