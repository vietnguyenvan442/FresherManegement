package com.example.fresher_management.repository;

import com.example.fresher_management.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RankRepository extends JpaRepository<Rank, Integer> {

        @Query("SELECT r FROM Rank r " +
                "JOIN Test t ON t.rank.id = r.id " +
                "JOIN Result res ON res.test.id = t.id " +
                "WHERE res.fresher.id = :fresher_id " +
                "AND r.id = :rank_id " +
                "GROUP BY r.id")
    public List<Rank> findRankByFresherId(@Param("fresher_id") int fresher_id, @Param("rank_id") int rank_id);
}
