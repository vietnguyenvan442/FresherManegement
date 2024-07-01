package com.example.fresher_management.repository;

import com.example.fresher_management.entity.Fresher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FresherRepository extends JpaRepository<Fresher, Integer> {
//    List<Fresher> findByCenterId(int centerId);
//    List<Fresher> findByCenterIdIn(List<Integer> centerIds);
}
