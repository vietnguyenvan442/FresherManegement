package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.Test;
import com.example.fresher_management.exception.ResourceNotFoundException;
import com.example.fresher_management.repository.TestRepository;
import com.example.fresher_management.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class TestServiceImpl implements TestService {
    @Autowired
    private TestRepository testRepository;

    @Transactional
    @Override
    public Test findById(int id) {
        log.info("Finding Test by ID: {}", id);
        Test test = testRepository.findById(id).orElse(null);
        if (test != null) {
            return test;
        } else {
            log.error("Test not found for ID = {}", id);
            throw new ResourceNotFoundException("Test not found for ID = " + id);
        }
    }
}
