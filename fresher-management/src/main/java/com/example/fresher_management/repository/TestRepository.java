package com.example.fresher_management.repository;

import com.example.fresher_management.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Integer> {
}
