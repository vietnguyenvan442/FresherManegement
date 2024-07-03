package com.example.fresher_management.service;

import com.example.fresher_management.entity.*;
import com.example.fresher_management.repository.FresherRepository;
import com.example.fresher_management.repository.LanguageRepository;
import com.example.fresher_management.repository.PositionRepository;
import com.example.fresher_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FresherService {

    @Autowired
    private FresherRepository fresherRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<Fresher> getAllFreshers() {
        return fresherRepository.findAll();
    }

//    @Transactional
//    public List<Fresher> getFreshersByCenterId(int centerId) {
//        return fresherRepository.findByCenterId(centerId);
//    }
//
//    @Transactional
//    public List<Fresher> getFreshersByCenterIds(List<Integer> centerIds) {
//        return fresherRepository.findByCenterIdIn(centerIds);
//    }

    @Transactional
    public Fresher addFresher(Fresher fresher) {
        // Set default position
        Position defaultPosition = positionRepository.findById(1).orElse(null);
        fresher.setPosition(defaultPosition);

        // Handle language
        if (fresher.getLanguage() != null) {
            Language existingLanguage = languageRepository.findById(fresher.getLanguage().getId()).orElse(null);
            if (existingLanguage != null) {
                fresher.setLanguage(existingLanguage);
            } else {
                Language newLanguage = languageRepository.save(fresher.getLanguage());
                fresher.setLanguage(newLanguage);
            }
        }

        return fresherRepository.save(fresher);
    }

    @Transactional
    public Fresher updateFresher(int id, Fresher fresherDetails) {
        Optional<Fresher> optionalFresher = fresherRepository.findById(id);
        if (!optionalFresher.isPresent()) {
            return null;
        }

        Fresher fresher = optionalFresher.get();
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
            Language existingLanguage = languageRepository.findById(fresherDetails.getLanguage().getId()).orElse(null);
            if (existingLanguage != null) {
                fresher.setLanguage(existingLanguage);
            } else {
                Language newLanguage = languageRepository.save(fresherDetails.getLanguage());
                fresher.setLanguage(newLanguage);
            }
        }

        return fresherRepository.save(fresher);
    }

    @Transactional
    public void deleteFresher(int id) {
        Optional<Fresher> optionalFresher = fresherRepository.findById(id);
        if (optionalFresher.isPresent()) {
            Fresher existingFresher = optionalFresher.get();
            existingFresher.setState(false);
            fresherRepository.save(existingFresher);
        } else {
            throw new RuntimeException("Fresher not found with id " + id);
        }
    }
}
