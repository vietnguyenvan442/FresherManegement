package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.Rank;
import com.example.fresher_management.repository.RankRepository;
import com.example.fresher_management.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankServiceImpl implements RankService {
    @Autowired
    private RankRepository rankRepository;

    @Override
    public List<Rank> findByFresherId(int fresher_id, int rank_id) {
        return rankRepository.findRankByFresherId(fresher_id, rank_id);
    }
}
