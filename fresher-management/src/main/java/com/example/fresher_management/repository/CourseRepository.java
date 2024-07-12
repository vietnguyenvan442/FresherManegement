package com.example.fresher_management.repository;

import com.example.fresher_management.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Date;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findById(int id);

    @Modifying
    @Transactional
    @Query("UPDATE Course c SET c.center.id = :newCenterId WHERE c.center.id = :centerId AND c.end_time > :currentDateTime")
    void updateCenterId(@Param("newCenterId") int newCenterId,
                        @Param("centerId") int centerId,
                        @Param("currentDateTime") Date currentDateTime);
}
