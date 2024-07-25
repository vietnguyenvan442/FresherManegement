package com.example.fresher_management.repository;

import com.example.fresher_management.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {

    public Area findByName(String name);
}
