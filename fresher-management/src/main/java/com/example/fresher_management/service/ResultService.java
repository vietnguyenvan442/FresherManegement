package com.example.fresher_management.service;

import com.example.fresher_management.entity.Result;

import java.util.List;

public interface ResultService {
    public List<Result> getResultsByFresher(int fresher_id);
    public Float getTotalScores(List<Result> results);
}
