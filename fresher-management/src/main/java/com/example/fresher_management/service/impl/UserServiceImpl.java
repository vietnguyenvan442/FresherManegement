package com.example.fresher_management.service.impl;

import com.example.fresher_management.dto.BearerToken;
import com.example.fresher_management.dto.LoginDto;
import com.example.fresher_management.entity.Admin;
import com.example.fresher_management.entity.Manager;
import com.example.fresher_management.entity.Position;
import com.example.fresher_management.entity.User;
import com.example.fresher_management.exception.ResourceNotFoundException;
import com.example.fresher_management.exception.UserAlreadyExistsException;
import com.example.fresher_management.exception.ValidationException;
import com.example.fresher_management.repository.UserRepository;
import com.example.fresher_management.service.PositionService;
import com.example.fresher_management.service.UserService;
import com.example.fresher_management.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PositionService positionService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    public User saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("User already exists with username: " + user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserByToken(String token) {
        String username = jwtUtil.getUsernameFromToken(token);
        User user = getUserByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with token: " + token);
        }
        return user;
    }

    @Override
    public BearerToken generateToken(LoginDto loginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        } catch (AuthenticationException e) {
            throw new ValidationException("Incorrect username or password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());
        final String accessToken = jwtUtil.generateToken(userDetails);
        final String refreshToken = jwtUtil.generateRefreshToken(userDetails);
        return new BearerToken(accessToken, refreshToken);
    }

    public void saveAdmin() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setCccd("01234");
        admin.setAddress("HN");
        admin.setDob("12-11-2002");
        admin.setName("Nguyen Viet");
        admin.setEmail("viet@gmail.com");
        admin.setSalary(100000);
        admin.setSdt("1234567");
        admin.setState(true);

        userRepository.save(admin);
    }

    public void saveManager() {
        Manager manager = new Manager();
        manager.setUsername("manager2");
        manager.setPassword(passwordEncoder.encode("123"));
        manager.setCccd("01234");
        manager.setAddress("NY");
        manager.setDob("12-11-2002");
        manager.setName("Tran C");
        manager.setEmail("c@gmail.com");
        manager.setSalary(1000);
        manager.setSdt("834928");
        manager.setState(true);

        Position position = positionService.findById(3);
        if (position == null) {
            throw new ResourceNotFoundException("Manager not found");
        }
        manager.setPosition(position);

        userRepository.save(manager);
    }
}
