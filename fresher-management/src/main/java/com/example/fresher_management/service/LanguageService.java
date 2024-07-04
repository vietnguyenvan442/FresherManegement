package com.example.fresher_management.service;

import com.example.fresher_management.entity.Language;
import com.example.fresher_management.entity.Manager;
import com.example.fresher_management.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;


    @Transactional
    public Language findById(int id){
        return languageRepository.findById(id).orElse(null);
    }


    @Transactional
    public Language save(Language language){
        return languageRepository.save(language);
    }
}
