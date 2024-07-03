package com.example.fresher_management.service;

import com.example.fresher_management.entity.Area;
import com.example.fresher_management.entity.Fresher;
import com.example.fresher_management.entity.Language;
import com.example.fresher_management.entity.Position;
import com.example.fresher_management.repository.FresherRepository;
import com.example.fresher_management.repository.LanguageRepository;
import com.example.fresher_management.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    public List<Fresher> getAllFreshers() {
        return fresherRepository.findAll();
    }

//    public List<Fresher> getFreshersByCenterId(int centerId) {
//        return fresherRepository.findByCenterId(centerId);
//    }
//
//    public List<Fresher> getFreshersByCenterIds(List<Integer> centerIds) {
//        return fresherRepository.findByCenterIdIn(centerIds);
//    }

    @Transactional
    public Fresher addFresher(Fresher fresher) {
        if (fresher.getLanguage() != null) {
            Language existingLanguage = languageRepository.findById(fresher.getLanguage().getId()).orElse(null);
            if (existingLanguage != null) {
                fresher.setLanguage(existingLanguage);
            } else {
                languageRepository.save(fresher.getLanguage());
            }
        }

        return fresherRepository.save(fresher);
    }
}
