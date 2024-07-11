package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.Language;
import com.example.fresher_management.exception.ResourceNotFoundException;
import com.example.fresher_management.repository.LanguageRepository;
import com.example.fresher_management.service.LanguageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    @Override
    @Transactional
    @Cacheable(value = "language", key = "#id")
    public Language findById(int id){
        log.info("Fetching language by id: {}", id);
        return languageRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    @CachePut(value = "language", key = "#id")
    public Language save(Language language){
        log.info("Saving language: {}", language);
        return languageRepository.save(language);
    }
}
