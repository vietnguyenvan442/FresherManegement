package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.Area;
import com.example.fresher_management.entity.Center;
import com.example.fresher_management.entity.Manager;
import com.example.fresher_management.exception.ResourceNotFoundException;
import com.example.fresher_management.repository.CenterRepository;
import com.example.fresher_management.service.AreaService;
import com.example.fresher_management.service.CenterService;
import com.example.fresher_management.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CenterServiceImpl implements CenterService {

    @Autowired
    private CenterRepository centerRepository;

    @Autowired
    private AreaService areaService;

    @Autowired
    private ManagerService managerService;

    @Transactional
    public List<Center> getAllCenters() {
        return centerRepository.findAll();
    }

    @Transactional
    public Center save(Center center){
        return centerRepository.save(center);
    }

    @Transactional
    public Center addCenter(Center center) {
        if (center.getArea() != null) {
            Area existingArea = areaService.findById(center.getArea().getId());
            if (existingArea != null) {
                center.setArea(existingArea);
            } else {
                areaService.save(center.getArea());
            }
        }

        if (center.getManager() != null) {
            Manager existingManager = managerService.findById(center.getManager().getId());
            if (existingManager != null) {
                center.setManager(existingManager);
            } else {
                managerService.save(center.getManager());
            }
        }

        return save(center);
    }

    @Transactional
    public Center updateCenter(int id, Center updatedCenter) {
        Optional<Center> optionalCenter = centerRepository.findById(id);
        if (optionalCenter.isPresent()) {
            Center existingCenter = optionalCenter.get();
            existingCenter.setName(updatedCenter.getName());
            existingCenter.setSdt(updatedCenter.getSdt());
            existingCenter.setAddress(updatedCenter.getAddress());
            existingCenter.setEmail(updatedCenter.getEmail());
            existingCenter.setDescription(updatedCenter.getDescription());
            existingCenter.setState(updatedCenter.isState());

            if (updatedCenter.getArea() != null) {
                Area existingArea = areaService.findById(updatedCenter.getArea().getId());
                if (existingArea != null) {
                    existingCenter.setArea(existingArea);
                } else {
                    areaService.save(updatedCenter.getArea());
                    existingCenter.setArea(updatedCenter.getArea());
                }
            }

            if (updatedCenter.getManager() != null) {
                Manager existingManager = managerService.findById(updatedCenter.getManager().getId());
                if (existingManager != null) {
                    existingCenter.setManager(existingManager);
                } else {
                    managerService.save(updatedCenter.getManager());
                    existingCenter.setManager(updatedCenter.getManager());
                }
            }

            return centerRepository.save(existingCenter);
        } else {
            throw new ResourceNotFoundException("Center not found with id " + id);
        }
    }

    @Transactional
    public void deleteCenter(int id) {
        Optional<Center> optionalCenter = centerRepository.findById(id);
        if (optionalCenter.isPresent()) {
            Center existingCenter = optionalCenter.get();
            existingCenter.setState(false);
            centerRepository.save(existingCenter);
        } else {
            throw new ResourceNotFoundException("Center not found with id " + id);
        }
    }

    @Transactional
    public Center findById(int id){
        return centerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Center not found with id " + id));
    }
}
