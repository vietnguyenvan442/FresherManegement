package com.example.fresher_management.service;

import com.example.fresher_management.entity.Position;
<<<<<<< HEAD

public interface PositionService {
    public Position findById(int id);
=======
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
>>>>>>> 9f1e8361f333996e63834602545a5a7b55b43b6f
}
