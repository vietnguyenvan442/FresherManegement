package com.example.fresher_management.service.impl;

import com.example.fresher_management.dto.StatFresherScoreRangeOutputDto;
import com.example.fresher_management.entity.Fresher;
import com.example.fresher_management.entity.Language;
import com.example.fresher_management.entity.User;
import com.example.fresher_management.exception.ResourceNotFoundException;
import com.example.fresher_management.repository.FresherRepository;
import com.example.fresher_management.service.*;
import com.example.fresher_management.validate.EmailFormatValidate;
import com.example.fresher_management.validate.UserValidate;
import com.example.fresher_management.validate.PhoneNumberFormatValidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
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
    private UserValidate userValidate;

    @Autowired
    private EmailFormatValidate emailFormatValidate;

    @Autowired
    private PhoneNumberFormatValidate phoneNumberFormatValidate;

    @Autowired
    private ResultService resultService;

    private static final List<String> SCORE_RANGES = Arrays.asList("9-10", "8-9", "7-8", "6-7", "5-6", "Below 5");


    @Override
    @Transactional
    @Cacheable(value = "fresher", key = "#id")
    public Fresher findById(int id) {
        log.info("Finding fresher by ID: {}", id);
        return fresherRepository.findByIdAndStateTrue(id)
                .orElseThrow(() -> {
                    log.error("Fresher not found with id {}", id);
                    return new ResourceNotFoundException("Fresher not found with id " + id);
                });
    }

    @Override
    @Transactional
    @Cacheable(value = "freshers", key = "#user")
    public List<Fresher> getFreshers(User user) {
        if ("ADMIN".equalsIgnoreCase(user.getRole().getName())) {
            return fresherRepository.findByStateTrue();
        } else if ("MANAGER".equalsIgnoreCase(user.getRole().getName())) {
            return fresherRepository.getFresherByManagerId(user.getId());
        }
        return null;
    }

    @Override
    @Transactional
    @CacheEvict(value = "freshers", allEntries = true)
    public Fresher addFresher(Fresher fresher) {
        log.info("Adding new fresher: {}", fresher);
        validateFresher(fresher);
        fresher.setRole(roleService.findById(1));
        fresher.setLanguage(getOrSaveLanguage(fresher.getLanguage()));
        fresher.setPassword(passwordEncoder.encode(fresher.getPassword()));
        Fresher savedFresher = fresherRepository.save(fresher);
        log.info("Saved fresher with ID: {}", savedFresher.getId());
        return savedFresher;
    }

    //not update ccccd
    @Override
    @Transactional
    @CachePut(value = "freshers", key = "#id")
    @CacheEvict(value = "freshers", allEntries = true)
    public Fresher updateFresher(int id, Fresher fresherDetails) {
        log.info("Updating fresher with ID: {}", id);
        Fresher fresher = findById(id);
        updateFresherDetails(fresher, fresherDetails);
        Fresher updatedFresher = fresherRepository.save(fresher);
        log.info("Updated fresher with ID: {}", updatedFresher.getId());
        return updatedFresher;
    }

    @Override
    @Transactional
    @CacheEvict(value = "freshers", allEntries = true)
    public void deleteFresher(int id) {
        log.info("Deleting fresher with ID: {}", id);
        Fresher existingFresher = findById(id);
        existingFresher.setState(false);
        fresherRepository.save(existingFresher);
        log.info("Deleted fresher with ID: {}", id);
    }

//    @Override
//    @Cacheable(value = "fresherScore", key = "#id")
//    public Float getScore(int id) {
//        log.info("Calculating score for fresher with ID: {}", id);
//        return resultService.getTotalScores(resultService.getResultsByFresher(id));
//    }

    @Override
    public List<Fresher> getSearchByName(String key, User user) {
        return searchFreshersByKey(key, user, "name");
    }

    @Override
    public List<Fresher> getSearchByEmail(String key, User user) {
        return searchFreshersByKey(key, user, "email");
    }

    @Override
    public List<Fresher> getSearchByLanguage(String key, User user) {
        return searchFreshersByKey(key, user, "language");
    }

    @Override
    public List<StatFresherScoreRangeOutputDto> getFresherScoreRangeStats(User user) {
        List<Object[]> results = new ArrayList<>();

        if ("ADMIN".equalsIgnoreCase(user.getRole().getName())) {
            results = fresherRepository.statFresherScoreRange();
        } else if ("MANAGER".equalsIgnoreCase(user.getRole().getName())) {
            results = fresherRepository.statFresherScoreRangeForManager(user.getId());
        }

        Map<String, Integer> resultMap = new HashMap<>();

        for (Object[] result : results) {
            String scoreRange = (String) result[0];
            Long count = ((Number) result[1]).longValue();
            resultMap.put(scoreRange, count.intValue());
        }

        List<StatFresherScoreRangeOutputDto> dtoList = new ArrayList<>();
        for (String range : SCORE_RANGES) {
            dtoList.add(new StatFresherScoreRangeOutputDto(range, resultMap.getOrDefault(range, 0)));
        }

        return dtoList;
    }

    private void validateFresher(Fresher fresher) {
        userValidate.validateMandatoryFields(fresher);
        emailFormatValidate.validateEmailFormat(fresher.getEmail());
        phoneNumberFormatValidate.validatePhoneNumberFormat(fresher.getSdt());
        userValidate.validateUniqueCCCD(fresher.getCccd());
        userValidate.validateUniqueUsername(fresher.getUsername());
        userValidate.validateUniqueEmail(fresher.getEmail());
    }

    private Language getOrSaveLanguage(Language language) {
        Language existingLanguage = languageService.findById(language.getId());
        if (existingLanguage != null) {
            return existingLanguage;
        } else {
            return languageService.save(language);
        }
    }

    private void updateFresherDetails(Fresher fresher, Fresher fresherDetails) {
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
            userValidate.validateUniqueEmail(fresherDetails.getEmail());
            fresher.setEmail(fresherDetails.getEmail());
        }
        if (fresherDetails.getSalary() >= 0 && fresher.getSalary() != fresherDetails.getSalary()) {
            fresher.setSalary(fresherDetails.getSalary());
        }
        if (fresherDetails.getLanguage() != null) {
            fresher.setLanguage(getOrSaveLanguage(fresherDetails.getLanguage()));
        }
    }

    private List<Fresher> searchFreshersByKey(String key, User user, String searchField) {
        log.info("Searching freshers by {}: {}", searchField, key);
        List<Fresher> freshers = getFreshers(user);
        List<Fresher> listSearch = new ArrayList<>();
        key = key.toLowerCase();

        for (Fresher fresher : freshers) {
            if (searchField.equals("name") && fresher.getName().toLowerCase().contains(key)) {
                listSearch.add(fresher);
            } else if (searchField.equals("email") && fresher.getEmail().toLowerCase().contains(key)) {
                listSearch.add(fresher);
            } else if (searchField.equals("language") && fresher.getLanguage().getName().toLowerCase().contains(key)) {
                listSearch.add(fresher);
            }
        }

        return listSearch;
    }
}
