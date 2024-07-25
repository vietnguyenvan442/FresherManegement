package com.example.fresher_management.service;

import com.example.fresher_management.entity.Language;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LanguageServiceTest {

    @Autowired
    private LanguageService languageService;

    @Test
    public void testFindById_Success(){
        Language language = languageService.findById(1);

        assertNotNull(language);
        assertEquals(1, language.getId());
        assertEquals("JAVA", language.getName());
    }

    @Test
    public void testFindById_Null(){
        Language language = languageService.findById(90);

        assertNull(language);
    }

    @Test
    public void testSave(){
        Language language = new Language();
        language.setName("ReactJs");
        Language language_saved = languageService.save(language);

        assertNotNull(language_saved);
        assertEquals("ReactJs", language_saved.getName());
    }
}
