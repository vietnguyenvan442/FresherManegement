package com.example.fresher_management.service;

import com.example.fresher_management.entity.Course;
import com.example.fresher_management.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Test
    public void testFindById_Success(){
        Course course = courseService.findById(1);
        assertNotNull(course);
        assertEquals(1, course.getId());
        assertEquals("Dao tao fresher VMO", course.getName());
    }

    @Test
    public void testFindById_Null(){
        Course course = courseService.findById(10);
        assertNull(course);
    }

    @Test
    public void testCheckCourseEnded_CourseEnded(){
        boolean result = courseService.checkCourseEnded(2);
        assertTrue(result);
    }

    @Test
    public void testCheckCourseEnded_CourseNotEnded(){
        boolean result = courseService.checkCourseEnded(1);
        assertFalse(result);
    }
}
