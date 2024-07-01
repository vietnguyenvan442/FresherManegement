package com.example.fresher_management.service;

import com.example.fresher_management.entity.Admin;
import com.example.fresher_management.entity.Manager;
import com.example.fresher_management.entity.Position;
import com.example.fresher_management.entity.User;
import com.example.fresher_management.repository.PositionRepository;
import com.example.fresher_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PositionRepository positionRepository;

    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void saveAdmin(){
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

//    public void saveManager(){
//        Manager manager = new Manager();
//        manager.setUsername("manager1");
//        manager.setPassword(passwordEncoder.encode("123"));
//        manager.setCccd("01234");
//        manager.setAddress("HN");
//        manager.setDob("12-11-2002");
//        manager.setName("Nguyen V");
//        manager.setEmail("v@gmail.com");
//        manager.setSalary(1000);
//        manager.setSdt("123456");
//        manager.setState(true);
//
//        Position position = positionRepository.findById(3)
//                .orElseThrow(() -> new RuntimeException("Manager not found"));
//        manager.setPosition(position);
//
//        userRepository.save(manager);
//    }

}
