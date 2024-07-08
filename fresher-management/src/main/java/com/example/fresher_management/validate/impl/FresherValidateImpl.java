package com.example.fresher_management.validate.impl;

import com.example.fresher_management.entity.Fresher;
import com.example.fresher_management.exception.ValidationException;
import com.example.fresher_management.repository.FresherRepository;
import com.example.fresher_management.validate.FresherValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class FresherValidateImpl implements FresherValidate {

    @Autowired
    private FresherRepository fresherRepository;

    public void validateMandatoryFields(Fresher fresher) {
        if (fresher.getUsername() == null || fresher.getUsername().isEmpty()) {
            throw new ValidationException("Username is required");
        }
        if (fresher.getPassword() == null || fresher.getPassword().isEmpty()) {
            throw new ValidationException("Password is required");
        }
        if (fresher.getName() == null || fresher.getName().isEmpty()) {
            throw new ValidationException("Name is required");
        }
        if (fresher.getDob() == null || fresher.getDob().isEmpty()) {
            throw new ValidationException("Date of birth is required");
        }
        if (fresher.getCccd() == null || fresher.getCccd().isEmpty()) {
            throw new ValidationException("CCCD is required");
        }
        if (fresher.getSdt() == null || fresher.getSdt().isEmpty()) {
            throw new ValidationException("Phone number is required");
        }
        if (fresher.getAddress() == null || fresher.getAddress().isEmpty()) {
            throw new ValidationException("Address is required");
        }
        if (fresher.getEmail() == null || fresher.getEmail().isEmpty()) {
            throw new ValidationException("Email is required");
        }
    }

    public void validateUniqueUsername(String username) {
        if (fresherRepository.findByUsername(username).isPresent()) {
            throw new ValidationException("Username already exists");
        }
    }

    public void validateUniqueCCCD(String cccd) {
        if (fresherRepository.findByCccd(cccd).isPresent()) {
            throw new ValidationException("CCCD already exists");
        }
    }

    public void validateUniqueEmail(String email) {
        if (fresherRepository.findByEmail(email).isPresent()) {
            throw new ValidationException("Email already exists");
        }
    }
}
