package com.example.fresher_management.repository;

import com.example.fresher_management.dto.StatCenterOutputDto;
import com.example.fresher_management.entity.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CenterRepository extends JpaRepository<Center, Integer>, CustomCenterRepository {

    Optional<Center> findByEmail(String email);

    Optional<Center> findBySdt(String sdt);

    List<Center> findByStateTrue();

    Optional<Center> findByIdAndStateTrue(int id);

    List<Center> getCenterByManagerId(int id);

    @Query("SELECT new com.example.fresher_management.dto.StatCenterOutputDto(c, COUNT(DISTINCT f)) " +
            "FROM Center c " +
            "JOIN Course co ON co.center.id = c.id " +
            "JOIN Record r ON r.course.id = co.id " +
            "JOIN Fresher f ON r.fresher.id = f.id " +
            "WHERE r.start_time >= :start_date " +
            "AND r.start_time <= :end_date " +
            "GROUP BY c")
    List<StatCenterOutputDto> statNumOfFresherToCenter(@Param("start_date") Date start_date, @Param("end_date") Date end_date);
}
