package com.example.fresher_management.validate.impl;

import com.example.fresher_management.entity.User;
import com.example.fresher_management.exception.ValidationException;
import com.example.fresher_management.repository.UserRepository;
import com.example.fresher_management.validate.UserValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidateImpl implements UserValidate {

    @Autowired
    private UserRepository userRepository;

    public void validateMandatoryFields(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new ValidationException("Username is required");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new ValidationException("Password is required");
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new ValidationException("Name is required");
        }
        if (user.getDob() == null || user.getDob().isEmpty()) {
            throw new ValidationException("Date of birth is required");
        }
        if (user.getCccd() == null || user.getCccd().isEmpty()) {
            throw new ValidationException("CCCD is required");
        }
        if (user.getSdt() == null || user.getSdt().isEmpty()) {
            throw new ValidationException("Phone number is required");
        }
        if (user.getAddress() == null || user.getAddress().isEmpty()) {
            throw new ValidationException("Address is required");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new ValidationException("Email is required");
        }
    }

    public void validateUniqueUsername(String username) {
        if (userRepository.findByUsername(username) != null) {
            throw new ValidationException("Username already exists");
        }
    }

    public void validateUniqueCCCD(String cccd) {
        if (userRepository.findByCccd(cccd).isPresent()) {
            throw new ValidationException("CCCD already exists");
        }
    }

    public void validateUniqueEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new ValidationException("Email already exists");
        }
    }
}
