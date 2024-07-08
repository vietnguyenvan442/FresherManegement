package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.Area;
import com.example.fresher_management.entity.Center;
import com.example.fresher_management.entity.Manager;
import com.example.fresher_management.exception.ResourceNotFoundException;
import com.example.fresher_management.repository.CenterRepository;
import com.example.fresher_management.service.AreaService;
import com.example.fresher_management.service.CenterService;
import com.example.fresher_management.service.ManagerService;
import com.example.fresher_management.validate.CenterValidate;
import com.example.fresher_management.validate.EmailFormatValidate;
import com.example.fresher_management.validate.PhoneNumberFormatValidate;
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

    @Autowired
    private CenterValidate centerValidate;

    @Autowired
    private PhoneNumberFormatValidate phoneNumberFormatValidate;

    @Autowired
    private EmailFormatValidate emailFormatValidate;

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
        centerValidate.validateMandatoryFields(center);
        phoneNumberFormatValidate.validatePhoneNumberFormat(center.getSdt());
        emailFormatValidate.validateEmailFormat(center.getEmail());
        centerValidate.validateUniqueEmail(center.getEmail());
        centerValidate.validateUniquePhongNumber(center.getSdt());

        Area existingArea = areaService.findById(center.getArea().getId());
        if (existingArea != null) {
            center.setArea(existingArea);
        } else {
            areaService.save(center.getArea());
        }

        Manager existingManager = managerService.findById(center.getManager().getId());
        if (existingManager != null) {
            center.setManager(existingManager);
        } else {
            managerService.save(center.getManager());
        }

        return save(center);
    }

    @Transactional
    public Center updateCenter(int id, Center updatedCenter) {
        Optional<Center> optionalCenter = centerRepository.findById(id);
        if (optionalCenter.isPresent()) {
            Center existingCenter = optionalCenter.get();

            if (updatedCenter.getName() != null && !existingCenter.getName().equals(updatedCenter.getName())){
                existingCenter.setName(updatedCenter.getName());
            }
            if (updatedCenter.getSdt() != null && !existingCenter.getSdt().equals(updatedCenter.getSdt())){
                phoneNumberFormatValidate.validatePhoneNumberFormat(updatedCenter.getSdt());
                centerValidate.validateUniquePhongNumber(updatedCenter.getSdt());
                existingCenter.setSdt(updatedCenter.getSdt());
            }
            if (updatedCenter.getAddress() != null && !existingCenter.getAddress().equals(updatedCenter.getAddress())){
                existingCenter.setAddress(updatedCenter.getAddress());
            }
            if (updatedCenter.getEmail() != null && !existingCenter.getEmail().equals(updatedCenter.getEmail())){
                emailFormatValidate.validateEmailFormat(updatedCenter.getEmail());
                centerValidate.validateUniqueEmail(updatedCenter.getEmail());
                existingCenter.setEmail(updatedCenter.getEmail());
            }
            if (updatedCenter.getDescription() != null && !existingCenter.getDescription().equals(updatedCenter.getDescription())){
                existingCenter.setDescription(updatedCenter.getDescription());
            }

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
