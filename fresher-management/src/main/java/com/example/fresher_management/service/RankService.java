package com.example.fresher_management.service;

import com.example.fresher_management.entity.Rank;

import java.util.List;

public interface RankService {
    public List<Rank> findByFresherId(int fresher_id, int rank_id);
}
