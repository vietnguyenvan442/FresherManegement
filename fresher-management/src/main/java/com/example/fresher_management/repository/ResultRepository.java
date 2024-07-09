package com.example.fresher_management.repository;

import com.example.fresher_management.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {

    @Query("SELECT r FROM Result r, Fresher f WHERE r.fresher.id = :fresher_id AND f.id = r.fresher.id AND f.state = TRUE")
    List<Result> getResultsByFresher(@Param("fresher_id") int fresher_id);
}
