package com.example.fresher_management.repository;

import com.example.fresher_management.entity.Fresher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FresherRepository extends JpaRepository<Fresher, Integer> {

    @Query("SELECT f FROM Fresher f, Center c, Record r WHERE c.manager.id = :manager_id AND r.center.id = c.id")
    List<Fresher> getFresherByManagerId(@Param("manager_id") Integer manager_id);
}
