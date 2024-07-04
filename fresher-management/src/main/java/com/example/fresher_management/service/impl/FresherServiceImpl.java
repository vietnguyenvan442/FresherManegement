package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.*;
import com.example.fresher_management.exception.ResourceNotFoundException;
import com.example.fresher_management.repository.FresherRepository;
import com.example.fresher_management.service.FresherService;
import com.example.fresher_management.service.LanguageService;
import com.example.fresher_management.service.PositionService;
import com.example.fresher_management.service.UserService;
import com.example.fresher_management.validate.FresherValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FresherServiceImpl implements FresherService {

    @Autowired
    private FresherRepository fresherRepository;

    @Autowired
    private PositionService positionService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private FresherValidate fresherValidate;

    @Transactional
    public Fresher findById(int id){
        return fresherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fresher not found with id " + id));
    }

    @Transactional
    public List<Fresher> getFreshers(String token){
        User user = userService.getUserByToken(token.substring(7));
        if (user.getPosition().getName().equalsIgnoreCase("ADMIN"))
            return fresherRepository.findAll();
        else if (user.getPosition   ().getName().equalsIgnoreCase("MANAGER"))
            return fresherRepository.getFresherByManagerId(user.getId());
        return null;
    }

    @Transactional
    public Fresher addFresher(Fresher fresher) {
        fresherValidate.validateMandatoryFields(fresher);

        // Set default position
        fresher.setPosition(positionService.findById(1));

        // Handle language
        if (fresher.getLanguage() != null) {
            Language existingLanguage = languageService.findById(fresher.getLanguage().getId());
            if (existingLanguage != null) {
                fresher.setLanguage(existingLanguage);
            } else {
                Language newLanguage = languageService.save(fresher.getLanguage());
                fresher.setLanguage(newLanguage);
            }
        }

        return fresherRepository.save(fresher);
    }

    @Transactional
    public Fresher updateFresher(int id, Fresher fresherDetails) {
        Fresher fresher = fresherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fresher not found with id " + id));

        fresher.setUsername(fresherDetails.getUsername());
        fresher.setPassword(passwordEncoder.encode(fresherDetails.getPassword()));
        fresher.setName(fresherDetails.getName());
        fresher.setDob(fresherDetails.getDob());
        fresher.setCccd(fresherDetails.getCccd());
        fresher.setSdt(fresherDetails.getSdt());
        fresher.setAddress(fresherDetails.getAddress());
        fresher.setEmail(fresherDetails.getEmail());
        fresher.setSalary(fresherDetails.getSalary());
        fresher.setState(fresherDetails.isState());

        if (fresherDetails.getLanguage() != null) {
            Language existingLanguage = languageService.findById(fresherDetails.getLanguage().getId());
            if (existingLanguage != null) {
                fresher.setLanguage(existingLanguage);
            } else {
                Language newLanguage = languageService.save(fresherDetails.getLanguage());
                fresher.setLanguage(newLanguage);
            }
        }

        return fresherRepository.save(fresher);
    }

    @Transactional
    public void deleteFresher(int id) {
        Fresher existingFresher = fresherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fresher not found with id " + id));

        existingFresher.setState(false);
        fresherRepository.save(existingFresher);
    }
}
