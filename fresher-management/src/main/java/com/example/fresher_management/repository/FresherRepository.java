package com.example.fresher_management.repository;

import com.example.fresher_management.dto.StatFresherScoreRangeOutputDto;
import com.example.fresher_management.entity.Fresher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FresherRepository extends JpaRepository<Fresher, Integer> {
    Optional<Fresher> findByIdAndStateTrue(int id);

    List<Fresher> findByStateTrue();

    Optional<Fresher> findByUsername(String username);

    Optional<Fresher> findByCccd(String cccd);

    Optional<Fresher> findByEmail(String email);

    @Query("SELECT f FROM Fresher f, Center c, Record r, Course co WHERE c.manager.id = :manager_id AND co.center.id = c.id AND r.course.id = co.id AND r.fresher.id = f.id AND f.state = TRUE GROUP BY f.id")
    List<Fresher> getFresherByManagerId(@Param("manager_id") Integer manager_id);

    @Query(value = "SELECT " +
            "CASE " +
            "    WHEN temp.avgPoint >= 9 THEN '9-10' " +
            "    WHEN temp.avgPoint >= 8 THEN '8-9' " +
            "    WHEN temp.avgPoint >= 7 THEN '7-8' " +
            "    WHEN temp.avgPoint >= 6 THEN '6-7' " +
            "    WHEN temp.avgPoint >= 5 THEN '5-6' " +
            "    ELSE 'Below 5' " +
            "END as scoreRange, COUNT(temp.fresherId) as count " +
            "FROM ( " +
            "    SELECT f.id as fresherId, AVG(r.point) as avgPoint " +
            "    FROM fresher f " +
            "    JOIN result r ON r.fresher_id = f.id " +
            "    GROUP BY f.id " +
            "    HAVING COUNT(r.id) = 3 " +
            ") as temp " +
            "GROUP BY scoreRange", nativeQuery = true)
    List<Object[]> statFresherScoreRange();

    @Query(value = "SELECT " +
            "CASE " +
            "    WHEN temp.avgPoint >= 9 THEN '9-10' " +
            "    WHEN temp.avgPoint >= 8 THEN '8-9' " +
            "    WHEN temp.avgPoint >= 7 THEN '7-8' " +
            "    WHEN temp.avgPoint >= 6 THEN '6-7' " +
            "    WHEN temp.avgPoint >= 5 THEN '5-6' " +
            "    ELSE 'Below 5' " +
            "END as scoreRange, COUNT(temp.fresherId) as count " +
            "FROM ( " +
            "    SELECT f.id as fresherId, AVG(r.point) as avgPoint " +
            "    FROM fresher f " +
            "    JOIN result r ON r.fresher_id = f.id " +
            "    JOIN record rec ON rec.fresher_id = f.id " +
            "    JOIN course c ON c.id = rec.course_id " +
            "    JOIN center cen ON cen.id = c.center_id " +
            "    WHERE cen.manager_id = :manager_id" +
            "    GROUP BY f.id " +
            "    HAVING COUNT(r.id) = 3 " +
            ") as temp " +
            "GROUP BY scoreRange", nativeQuery = true)
    List<Object[]> statFresherScoreRangeForManager(@Param("manager_id") int manager_id);
}
