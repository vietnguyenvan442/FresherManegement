package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.Role;
import com.example.fresher_management.exception.ResourceNotFoundException;
import com.example.fresher_management.repository.RoleRepository;
import com.example.fresher_management.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public Role findById(int id) {
        log.info("Fetching role by id: {}", id);
        return roleRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Role not found with id: {}", id);
                    return new ResourceNotFoundException("Role not found with id " + id);
                });
    }
}
