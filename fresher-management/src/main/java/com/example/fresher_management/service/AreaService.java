package com.example.fresher_management.service;

import com.example.fresher_management.entity.Area;

import java.util.List;

public interface AreaService {
    public List<Area> getAllAreas();
    public Area save(Area area);
    public Area findById(int id);
}
