package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.Course;
import com.example.fresher_management.repository.CourseRepository;
import com.example.fresher_management.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course findById(int id) {
        log.info("Fetching course with ID: {}", id);
        return courseRepository.findById(id);
    }

    @Override
    public boolean checkCourseEnded(int id) {
        Course course = findById(id);
        boolean isEnded = course.getEnd_time().compareTo(Date.valueOf(LocalDate.now())) < 0;
        log.error("Course with ID: {} has ended: {}", id, isEnded);
        return isEnded;
    }

    @Override
    public void updateCenterId(int newCenterId, int centerId, Date currentDateTime) {
        log.info("Updating center ID from {} to {} for courses ending before {}", centerId, newCenterId, currentDateTime);
        courseRepository.updateCenterId(newCenterId, centerId, currentDateTime);
    }
}
