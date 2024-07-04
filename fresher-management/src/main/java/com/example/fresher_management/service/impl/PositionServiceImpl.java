package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.Position;
import com.example.fresher_management.exception.ResourceNotFoundException;
import com.example.fresher_management.repository.PositionRepository;
import com.example.fresher_management.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Transactional
    public Position findById(int id){
        return positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position not found with id " + id));
    }
}
