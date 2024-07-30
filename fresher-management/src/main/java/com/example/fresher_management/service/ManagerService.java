package com.example.fresher_management.service;

import com.example.fresher_management.entity.Manager;

import java.util.List;

public interface ManagerService {
    public List<Manager> getAllManagers();

    public Manager save(Manager manager);

    public Manager findById(int id);
}
