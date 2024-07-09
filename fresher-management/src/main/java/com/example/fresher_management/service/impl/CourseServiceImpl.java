package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.Course;
import com.example.fresher_management.repository.CourseRepository;
import com.example.fresher_management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course findById(int id) {
        return courseRepository.findById(id);
    }

    @Override
    public boolean checkCourseEnded(int id) {
        Course course = findById(id);
        return course.getEnd_time().compareTo(Date.valueOf(LocalDate.now())) < 0;
    }
}
