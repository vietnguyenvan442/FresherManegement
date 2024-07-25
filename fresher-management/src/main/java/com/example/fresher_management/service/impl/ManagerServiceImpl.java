package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.Manager;
import com.example.fresher_management.repository.ManagerRepository;
import com.example.fresher_management.service.ManagerService;
import com.example.fresher_management.validate.EmailFormatValidate;
import com.example.fresher_management.validate.PhoneNumberFormatValidate;
import com.example.fresher_management.validate.UserValidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private UserValidate userValidate;

    @Autowired
    private PhoneNumberFormatValidate phoneNumberFormatValidate;

    @Autowired
    private EmailFormatValidate emailFormatValidate;

    @Override
    @Transactional
    public List<Manager> getAllManagers() {
        log.info("Fetching all managers");
        return managerRepository.findAll();
    }

    @Override
    @Transactional
    public Manager save(Manager manager) {
        log.info("Saving manager: {}", manager.getName());
        phoneNumberFormatValidate.validatePhoneNumberFormat(manager.getSdt());
        emailFormatValidate.validateEmailFormat(manager.getEmail());
        userValidate.validateMandatoryFields(manager);
        userValidate.validateUniqueCCCD(manager.getCccd());
        userValidate.validateUniqueEmail(manager.getEmail());
        userValidate.validateUniqueUsername(manager.getUsername());

        log.info("Saved manager: {}", manager.getName());
        return managerRepository.save(manager);
    }

    @Override
    @Transactional
    public Manager findById(int id) {
        log.info("Fetching manager by id: {}", id);
        return managerRepository.findById(id).orElse(null);
    }
}
