package com.example.fresher_management.service;

import com.example.fresher_management.entity.Center;
import com.example.fresher_management.repository.CenterRepository;
import com.example.fresher_management.repository.ManagerRepository;
import com.example.fresher_management.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CenterService {

    @Autowired
    private CenterRepository centerRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private AreaRepository areaRepository;

    public Center addCenter(Center center, int managerId, int areaId) {
        center.setManager(managerRepository.findById(managerId).orElse(null));
        center.setArea(areaRepository.findById(areaId).orElse(null));
        return centerRepository.save(center);
    }
}
