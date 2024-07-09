package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.*;
import com.example.fresher_management.exception.ResourceNotFoundException;
import com.example.fresher_management.repository.FresherRepository;
import com.example.fresher_management.service.*;
import com.example.fresher_management.validate.EmailFormatValidate;
import com.example.fresher_management.validate.FresherValidate;
import com.example.fresher_management.validate.PhoneNumberFormatValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FresherServiceImpl implements FresherService {

    @Autowired
    private FresherRepository fresherRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private FresherValidate fresherValidate;

    @Autowired
    private EmailFormatValidate emailFormatValidate;

    @Autowired
    private PhoneNumberFormatValidate phoneNumberFormatValidate;

    @Autowired
    private ResultService resultService;

    @Override
    @Transactional
    public Fresher findById(int id) {
        return fresherRepository.findByIdAndStateTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fresher not found with id " + id));
    }

    @Override
    @Transactional
    public List<Fresher> getFreshers(String token) {
        User user = userService.getUserByToken(token.substring(7));
        if (user.getRole().getName().equalsIgnoreCase("ADMIN"))
            return fresherRepository.findByStateTrue();
        else if (user.getRole().getName().equalsIgnoreCase("MANAGER"))
            return fresherRepository.getFresherByManagerId(user.getId());
        return null;
    }

    @Override
    @Transactional
    public Fresher addFresher(Fresher fresher) {
        fresherValidate.validateMandatoryFields(fresher);
        emailFormatValidate.validateEmailFormat(fresher.getEmail());
        phoneNumberFormatValidate.validatePhoneNumberFormat(fresher.getSdt());
        fresherValidate.validateUniqueCCCD(fresher.getCccd());
        fresherValidate.validateUniqueUsername(fresher.getUsername());
        fresherValidate.validateUniqueEmail(fresher.getEmail());

        // Set default position
        fresher.setRole(roleService.findById(1));

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

        fresher.setPassword(passwordEncoder.encode(fresher.getPassword()));
        return fresherRepository.save(fresher);
    }

    @Override
    @Transactional //not update ccccd
    public Fresher updateFresher(int id, Fresher fresherDetails) {
        Fresher fresher = fresherRepository.findByIdAndStateTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fresher not found with id " + id));

        if (fresherDetails.getPassword() != null && !fresher.getPassword().equals(fresherDetails.getPassword())) {
            fresher.setPassword(passwordEncoder.encode(fresherDetails.getPassword()));
        }
        if (fresherDetails.getName() != null && !fresher.getName().equals(fresherDetails.getName())) {
            fresher.setName(fresherDetails.getName());
        }
        if (fresherDetails.getDob() != null && !fresher.getDob().equals(fresherDetails.getDob())) {
            fresher.setDob(fresherDetails.getDob());
        }
        if (fresherDetails.getSdt() != null && !fresher.getSdt().equals(fresherDetails.getSdt())) {
            phoneNumberFormatValidate.validatePhoneNumberFormat(fresherDetails.getSdt());
            fresher.setSdt(fresherDetails.getSdt());
        }
        if (fresherDetails.getAddress() != null && !fresher.getAddress().equals(fresherDetails.getAddress())) {
            fresher.setAddress(fresherDetails.getAddress());
        }
        if (fresherDetails.getEmail() != null && !fresher.getEmail().equals(fresherDetails.getEmail())) {
            emailFormatValidate.validateEmailFormat(fresherDetails.getEmail());
            fresherValidate.validateUniqueEmail(fresherDetails.getEmail());
            fresher.setEmail(fresherDetails.getEmail());
        }
        if (fresherDetails.getDob() != null && !fresher.getDob().equals(fresherDetails.getDob())) {
            fresher.setDob(fresherDetails.getDob());
        }
        if (fresherDetails.getSalary() >= 0 && fresher.getSalary() != fresherDetails.getSalary()) {
            fresher.setSalary(fresherDetails.getSalary());
        }


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

    @Override
    @Transactional
    public void deleteFresher(int id) {
        Fresher existingFresher = findById(id)  ;

        existingFresher.setState(false);
        fresherRepository.save(existingFresher);
    }

    @Override
    public Float getScore(int id) {
        return resultService.getTotalScores(resultService.getResultsByFresher(id));
    }

    @Override
    public List<Fresher> getSearchByName(String key, String token) {
        List<Fresher> freshers = getFreshers(token);
        List<Fresher> listSearch = new ArrayList<>();

        for (Fresher fresher : freshers) {
            if (fresher.getName().toLowerCase().contains(key.toLowerCase())) {
                listSearch.add(fresher);
            }
        }

        return listSearch;
    }

    @Override
    public List<Fresher> getSearchByEmail(String key, String token) {
        List<Fresher> freshers = getFreshers(token);
        List<Fresher> listSearch = new ArrayList<>();

        for (Fresher fresher : freshers) {
            if (fresher.getEmail().toLowerCase().contains(key.toLowerCase())) {
                listSearch.add(fresher);
            }
        }

        return listSearch;
    }

    @Override
    public List<Fresher> getSearchByLanguage(String key, String token) {
        List<Fresher> freshers = getFreshers(token);
        List<Fresher> listSearch = new ArrayList<>();

        for (Fresher fresher : freshers) {
            if (fresher.getLanguage().getName().toLowerCase().contains(key.toLowerCase())) {
                listSearch.add(fresher);
            }
        }

        return listSearch;
    }
}
