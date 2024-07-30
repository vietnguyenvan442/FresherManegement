package com.example.fresher_management.validate.impl;

import com.example.fresher_management.entity.Center;
import com.example.fresher_management.exception.ValidationException;
import com.example.fresher_management.repository.CenterRepository;
import com.example.fresher_management.validate.CenterValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CenterValidateImpl implements CenterValidate {
    @Autowired
    private CenterRepository centerRepository;

    @Override
    public void validateMandatoryFields(Center center) {
        if (center.getName() == null || center.getName().isEmpty()) {
            throw new ValidationException("Name is required");
        }
        if (center.getSdt() == null || center.getSdt().isEmpty()) {
            throw new ValidationException("Phone number is required");
        }
        if (center.getAddress() == null || center.getAddress().isEmpty()) {
            throw new ValidationException("Address is required");
        }
        if (center.getEmail() == null || center.getEmail().isEmpty()) {
            throw new ValidationException("Email is required");
        }
        if (center.getArea() == null) {
            throw new ValidationException("Area is required");
        }
        if (center.getManager() == null) {
            throw new ValidationException("Manager s'Center is required");
        }
    }

    @Override
    public void validateUniqueEmail(String email) {
        if (centerRepository.findByEmail(email).isPresent()) {
            throw new ValidationException("Email already exists");
        }
    }

    @Override
    public void validateUniquePhongNumber(String sdt) {
        if (centerRepository.findBySdt(sdt).isPresent()) {
            throw new ValidationException("Sdt already exists");
        }
    }
}
