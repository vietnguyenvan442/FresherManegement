package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.Language;
import com.example.fresher_management.exception.ResourceNotFoundException;
import com.example.fresher_management.repository.LanguageRepository;
import com.example.fresher_management.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    @Transactional
    public Language findById(int id){
        return languageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Language not found with id " + id));
    }

    @Transactional
    public Language save(Language language){
        return languageRepository.save(language);
    }
}
