package com.example.fresher_management.service;

import com.example.fresher_management.dto.ResultDto;
import com.example.fresher_management.entity.Result;
import com.example.fresher_management.entity.User;

import java.util.List;

public interface ResultService {
    public List<Result> getResultsByFresher(int fresher_id);
    public Float getTotalScores(List<Result> results);
    public Result save(ResultDto result, User user);
}
