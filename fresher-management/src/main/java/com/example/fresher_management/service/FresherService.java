package com.example.fresher_management.service;

import com.example.fresher_management.dto.FresherDto;
import com.example.fresher_management.entity.Fresher;
import com.example.fresher_management.entity.Language;
import com.example.fresher_management.entity.Position;
import com.example.fresher_management.repository.FresherRepository;
import com.example.fresher_management.repository.LanguageRepository;
import com.example.fresher_management.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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


    public Fresher addFresher(FresherDto fresherDto) {
        Fresher fresher = new Fresher();
        fresher.setUsername(fresherDto.getUsername());
        fresher.setPassword(passwordEncoder.encode(fresherDto.getPassword()));
        fresher.setName(fresherDto.getName());
        fresher.setDob(fresherDto.getDob());
        fresher.setCccd(fresherDto.getCccd());
        fresher.setSdt(fresherDto.getSdt());
        fresher.setAddress(fresherDto.getAddress());
        fresher.setEmail(fresherDto.getEmail());
        fresher.setSalary(fresherDto.getSalary());
        fresher.setState(fresherDto.isState());

        Position position = positionRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Position not found"));
        fresher.setPosition(position);

        Language language = languageRepository.findById(fresherDto.getLanguageId())
                .orElseThrow(() -> new RuntimeException("Language not found"));
        fresher.setLanguage(language);

        return fresherRepository.save(fresher);
    }
}
