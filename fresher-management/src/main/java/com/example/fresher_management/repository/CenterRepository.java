package com.example.fresher_management.repository;

import com.example.fresher_management.entity.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CenterRepository extends JpaRepository<Center, Integer>, CustomCenterRepository {

    Optional<Center> findByEmail(String email);

    Optional<Center> findBySdt(String sdt);
}
