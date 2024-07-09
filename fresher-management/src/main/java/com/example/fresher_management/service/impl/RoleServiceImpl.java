package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.Role;
import com.example.fresher_management.exception.ResourceNotFoundException;
import com.example.fresher_management.repository.RoleRepository;
import com.example.fresher_management.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Role findById(int id){
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + id));
    }
}
