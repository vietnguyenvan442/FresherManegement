package com.example.fresher_management.service;

import com.example.fresher_management.entity.Position;
import com.example.fresher_management.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;


    @Transactional
    public Position findById(int id){
        return positionRepository.findById(id).orElse(null);
    }
}
