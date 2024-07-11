package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.Result;
import com.example.fresher_management.exception.ScoresException;
import com.example.fresher_management.repository.ResultRepository;
import com.example.fresher_management.service.ResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private ResultRepository resultRepository;

    @Override
    @Cacheable(value = "results", key = "#fresher_id")
    public List<Result> getResultsByFresher(int fresher_id) {
        log.info("Fetching results by fresher ID: {}", fresher_id);
        return resultRepository.getResultsByFresher(fresher_id);
    }

    @Override
    public Float getTotalScores(List<Result> results) {
        log.info("Calculating total scores for results: {}", results);
        if (results.isEmpty()) throw new ScoresException("Fresher hasn't taken any tests yet");
        if (results.size() < 3) throw new ScoresException("Fresher has not taken all 3 tests");

        Float res = 0f;
        for (Result result : results) {
            res += result.getPoint();
        }
        return res / 3;
    }
}
