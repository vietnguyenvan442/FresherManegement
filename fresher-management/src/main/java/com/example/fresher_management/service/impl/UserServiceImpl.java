package com.example.fresher_management.service.impl;

import com.example.fresher_management.dto.BearerToken;
import com.example.fresher_management.dto.LoginDto;
import com.example.fresher_management.entity.User;
import com.example.fresher_management.exception.ResourceNotFoundException;
import com.example.fresher_management.exception.AlreadyExistsException;
import com.example.fresher_management.exception.ValidationException;
import com.example.fresher_management.repository.UserRepository;
import com.example.fresher_management.service.RoleService;
import com.example.fresher_management.service.UserService;
import com.example.fresher_management.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public User saveUser(User user) {
        log.info("Saving user: {}", user);
        if (userRepository.findByUsername(user.getUsername()) != null) {
            log.error("User already exists with username: {}", user.getUsername());
            throw new AlreadyExistsException("User already exists with username: " + user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        log.info("Fetching user by username: {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserByToken(String token) {
        log.info("Fetching user by token");
        String username = jwtUtil.getUsernameFromToken(token.substring(7));
        User user = getUserByUsername(username);
        if (user == null) {
            log.error("User not found with token: {}", token);
            throw new ResourceNotFoundException("User not found with token: " + token);
        }
        return user;
    }

    @Override
    public BearerToken generateToken(LoginDto loginDto) {
        log.info("Generating token for login dto: {}", loginDto);
        if (!checkState(loginDto.getUsername())) {
            log.error("Incorrect username or password");
            throw new ValidationException("Incorrect username or password");
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        } catch (AuthenticationException e) {
            log.error("Incorrect username or password");
            throw new ValidationException("Incorrect username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());
        final String accessToken = jwtUtil.generateToken(userDetails);
        final String refreshToken = jwtUtil.generateRefreshToken(userDetails);
        return new BearerToken(accessToken, refreshToken);
    }

    @Override
    public boolean checkState(String username) {
        log.info("Checking state for username: {}", username);
        User user = getUserByUsername(username);
        if (user == null) return false;
        return user.isState();
    }

//    public void saveAdmin() {
//        log.info("Saving default admin");
//        Admin admin = new Admin();
//        admin.setUsername("admin");
//        admin.setPassword(passwordEncoder.encode("123"));
//        admin.setCccd("01234");
//        admin.setAddress("HN");
//        admin.setDob("12-11-2002");
//        admin.setName("Nguyen Viet");
//        admin.setEmail("viet@gmail.com");
//        admin.setSalary(100000);
//        admin.setSdt("1234567");
//        admin.setState(true);
//
//        userRepository.save(admin);
//    }

//    public void saveManager() {
//        log.info("Saving default manager");
//        Manager manager = new Manager();
//        manager.setUsername("manager2");
//        manager.setPassword(passwordEncoder.encode("123"));
//        manager.setCccd("01234");
//        manager.setAddress("NY");
//        manager.setDob("12-11-2002");
//        manager.setName("Tran C");
//        manager.setEmail("c@gmail.com");
//        manager.setSalary(1000);
//        manager.setSdt("834928");
//        manager.setState(true);
//
//        Role role = roleService.findById(3);
//        if (role == null) {
//            log.error("Manager role not found");
//            throw new ResourceNotFoundException("Manager role not found");
//        }
//        manager.setRole(role);
//
//        userRepository.save(manager);
//    }
}
