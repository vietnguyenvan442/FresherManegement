package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.User;
import com.example.fresher_management.exception.ResourceNotFoundException;
import com.example.fresher_management.repository.UserRepository;
import com.example.fresher_management.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new ResourceNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public UserDetails getUserFromToken(String token) {
        String username = jwtUtil.getUsernameFromToken(token);
<<<<<<< HEAD:fresher-management/src/main/java/com/example/fresher_management/service/impl/UserDetailsServiceImpl.java
        if (username == null) {
            throw new ResourceNotFoundException("User not found with token: " + token);
        }
        return loadUserByUsername(username);
=======
        if (username != null) {
            return loadUserByUsername(username);
        }
        return null;
>>>>>>> 9f1e8361f333996e63834602545a5a7b55b43b6f:fresher-management/src/main/java/com/example/fresher_management/service/UserDetailsServiceImpl.java
    }

}
