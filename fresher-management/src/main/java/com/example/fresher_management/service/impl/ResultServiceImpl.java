package com.example.fresher_management.service.impl;

import com.example.fresher_management.dto.ResultDto;
import com.example.fresher_management.entity.Fresher;
import com.example.fresher_management.entity.Rank;
import com.example.fresher_management.entity.Result;
import com.example.fresher_management.entity.Test;
import com.example.fresher_management.exception.ForbiddenException;
import com.example.fresher_management.exception.RankTestHasScoreException;
import com.example.fresher_management.exception.ScoresException;
import com.example.fresher_management.exception.ValidationException;
import com.example.fresher_management.repository.ResultRepository;
import com.example.fresher_management.service.FresherService;
import com.example.fresher_management.service.RankService;
import com.example.fresher_management.service.ResultService;
import com.example.fresher_management.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Slf4j
@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private TestService testService;

    @Autowired
    private FresherService fresherService;

    @Autowired
    private RankService rankService;

    @Override
    public List<Result> getResultsByFresher(int fresher_id) {
        log.info("Fetching results by fresher ID: {}", fresher_id);
        return resultRepository.getResultsByFresher(fresher_id);
    }

    @Override
    public Float getTotalScores(List<Result> results) {
        log.info("Calculating total scores for results: {}", results);
        if (results.isEmpty()) {
            log.error("Fresher hasn't taken any tests yet");
            throw new ScoresException("Fresher hasn't taken any tests yet");
        }
        if (results.size() < 3) {
            log.error("Fresher has not taken all 3 tests");
            throw new ScoresException("Fresher has not taken all 3 tests");
        }

        Float res = 0f;
        for (Result result : results) {
            res += result.getPoint();
        }
        return res / 3;
    }

    @Override
    public Result save(ResultDto resultDto, String token) {
        log.info("Saving a new resultDto for Fresher ID: " + resultDto.getFresher_id());

        Test test = testService.findById(resultDto.getTest_id());
        Fresher fresher = fresherService.findById(resultDto.getFresher_id());

        List<Fresher> freshers = fresherService.getFreshers(token);
        if (!freshers.contains(fresher)) throw new ForbiddenException("Manager does not have the right to add points to this fresher ID: " + fresher.getId());

        List<Rank> ranks = rankService.findByFresherId(fresher.getId(), test.getRank().getId());
        if (!ranks.isEmpty()) throw new RankTestHasScoreException("The rank of this test already has points");

        Result result = new Result();
        result.setFresher(fresher);
        result.setTest(test);
        result.setPoint(resultDto.getPoint());
        result.setStart_test(isValidLocalDateTime(resultDto.getStart_test()));
        result.setEnd_test(isValidLocalDateTime(resultDto.getEnd_test()));

        log.info("Result added successfully");
        return resultRepository.save(result);
    }

    protected LocalDateTime isValidLocalDateTime(String dateTimeString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new ValidationException("Time is not in the correct LocalDateTime format (yyyy-MM-dd HH:mm:ss)");
        }
    }

}
