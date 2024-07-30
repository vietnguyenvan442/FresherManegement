package com.example.fresher_management.repository;

import com.example.fresher_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    public Optional<User> findByCccd(String cccd);

    public Optional<User> findByEmail(String email);
}
