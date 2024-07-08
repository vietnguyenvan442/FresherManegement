package com.example.fresher_management.service.impl;

import com.example.fresher_management.entity.Result;
import com.example.fresher_management.exception.ScoresException;
import com.example.fresher_management.repository.ResultRepository;
import com.example.fresher_management.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private ResultRepository resultRepository;

    @Override
    public List<Result> getResultsByFresher(int fresher_id) {
        return resultRepository.getResultsByFresher(fresher_id);
    }

    @Override
    public Float getTotalScores(List<Result> results) {
        if (results.isEmpty()) throw new ScoresException("Fresher haven't taken any tests yet");
        if (results.size() < 3) throw new ScoresException("Fresher have not taken all 3 tests");

        Float res = Float.valueOf(0);
        for (Result result: results){
            res += result.getPoint();
        }
        return res/3;
    }
}
