package com.example.fresher_management.service;

import com.example.fresher_management.entity.Course;

public interface CourseService {
    public Course findById(int id);
    public boolean checkCourseEnded(int id);
}
