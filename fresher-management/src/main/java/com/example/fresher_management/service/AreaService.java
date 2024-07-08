package com.example.fresher_management.service;

import com.example.fresher_management.entity.Area;
<<<<<<< HEAD

import java.util.List;

public interface AreaService {
    public List<Area> getAllAreas();
    public Area save(Area area);
    public Area findById(int id);
=======
import com.example.fresher_management.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AreaService {
    @Autowired
    private AreaRepository areaRepository;

    @Transactional
    public Area findById(int id){
        return areaRepository.findById(id).orElse(null);
    }

    @Transactional
    public Area save(Area area){
        return areaRepository.save(area);
    }
>>>>>>> 9f1e8361f333996e63834602545a5a7b55b43b6f
}
