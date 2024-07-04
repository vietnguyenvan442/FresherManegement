package com.example.fresher_management.repository;

import com.example.fresher_management.entity.Merger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MergerRepository extends JpaRepository<Merger, Integer> {

}
