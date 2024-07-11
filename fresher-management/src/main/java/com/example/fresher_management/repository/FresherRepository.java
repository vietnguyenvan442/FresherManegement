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

    @Query("SELECT new com.example.fresher_management.dto.StatFresherScoreRangeOutputDto(" +
            "CASE " +
            "WHEN AVG(r.point) >= 9 THEN '9-10' " +
            "WHEN AVG(r.point) >= 8 THEN '8-9' " +
            "WHEN AVG(r.point) >= 7 THEN '7-8' " +
            "WHEN AVG(r.point) >= 6 THEN '6-7' " +
            "WHEN AVG(r.point) >= 5 THEN '5-6' " +
            "ELSE 'Below 5' END, COUNT(f)) " +
            "FROM Fresher f " +
            "JOIN f.listResult r " +
            "JOIN Record re ON re.fresher.id = f.id " +
            "WHERE re.start_time >= :startDate " +
            "AND re.end_time <= :endDate " +
            "AND f.state = TRUE " +
            "GROUP BY " +
            "CASE " +
            "WHEN AVG(r.point) >= 9 THEN '9-10' " +
            "WHEN AVG(r.point) >= 8 THEN '8-9' " +
            "WHEN AVG(r.point) >= 7 THEN '7-8' " +
            "WHEN AVG(r.point) >= 6 THEN '6-7' " +
            "WHEN AVG(r.point) >= 5 THEN '5-6' " +
            "ELSE 'Below 5' END " +
            "HAVING COUNT(r.id) = 3")
    List<StatFresherScoreRangeOutputDto> statFresherScoreRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
