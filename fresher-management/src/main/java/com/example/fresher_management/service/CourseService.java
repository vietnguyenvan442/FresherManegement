package com.example.fresher_management.service;

import com.example.fresher_management.entity.Course;

import java.sql.Date;

public interface CourseService {
    public Course findById(int id);

    public boolean checkCourseEnded(int id);

    void updateCenterId(int newCenterId, int centerId, Date currentDateTime);
}
