package com.example.fresher_management.repository;

import com.example.fresher_management.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findById(int id);
}
