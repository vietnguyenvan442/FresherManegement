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

    public Center addCenter(Center center) {
        return centerRepository.save(center);
    }
}
