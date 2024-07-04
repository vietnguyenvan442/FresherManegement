package com.example.fresher_management.service;

import com.example.fresher_management.entity.Center;
import java.util.List;

public interface CenterService {
    public List<Center> getAllCenters();
    public Center save(Center center);
    public Center addCenter(Center center);
    public Center updateCenter(int id, Center updatedCenter);
    public void deleteCenter(int id);
    public Center findById(int id);
}
