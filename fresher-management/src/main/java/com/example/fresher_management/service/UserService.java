package com.example.fresher_management.service;

import com.example.fresher_management.dto.BearerToken;
import com.example.fresher_management.dto.LoginDto;
import com.example.fresher_management.entity.User;

public interface UserService {
    public User saveUser(User user);
    public User getUserByUsername(String username);
    public User getUserByToken(String token);
    public BearerToken generateToken(LoginDto loginDto);
}
