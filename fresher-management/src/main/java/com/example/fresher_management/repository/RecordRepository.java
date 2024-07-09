package com.example.fresher_management.repository;

import com.example.fresher_management.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {

    @Query("SELECT r FROM Record r WHERE r.fresher.id = :fresherId AND r.end_time IS NULL ORDER BY r.start_time DESC")
    List<Record> findLateActiveRecordsByFresherId(@Param("fresherId") Integer fresherId);

    @Query("SELECT r FROM Record r, Course c, Fresher f WHERE c.id = :course_id AND f.id = :fresher_id AND r.course.id = c.id AND r.fresher.id = f.id GROUP BY r.id")
    List<Record> findFresherToCourse(@Param("course_id") int course_id, @Param("fresher_id") int fresher_id);
}
