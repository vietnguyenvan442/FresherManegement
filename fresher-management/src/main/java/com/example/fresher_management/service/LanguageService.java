package com.example.fresher_management.service;

import com.example.fresher_management.entity.Language;

public interface LanguageService {
    public Language findById(int id);

    public Language save(Language language);
}
