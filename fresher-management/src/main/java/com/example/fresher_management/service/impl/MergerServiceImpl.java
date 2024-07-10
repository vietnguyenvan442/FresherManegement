package com.example.fresher_management.service.impl;

import com.example.fresher_management.dto.MergerDto;
import com.example.fresher_management.entity.Center;
import com.example.fresher_management.entity.Merger;
import com.example.fresher_management.exception.ValidationException;
import com.example.fresher_management.repository.MergerRepository;
import com.example.fresher_management.service.CenterService;
import com.example.fresher_management.service.CourseService;
import com.example.fresher_management.service.MergerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;

@Slf4j
@Service
public class MergerServiceImpl implements MergerService {

    @Autowired
    private MergerRepository mergerRepository;

    @Autowired
    private CenterService centerService;

    @Autowired
    private CourseService courseService;

    @Override
    @Transactional
    public Merger addMerger(MergerDto mergerDto){
        log.info("Adding merger with dto: {}", mergerDto);

        Center centerFirst = centerService.findById(mergerDto.getCenter_first_id());
        Center centerSecond = centerService.findById(mergerDto.getCenter_second_id());
        Center centerNew;

        if (mergerDto.getCenter_new().getId() == 0){
            centerNew = centerService.save(mergerDto.getCenter_new());
        } else {
            centerNew = centerService.updateById(mergerDto.getCenter_new().getId(), mergerDto.getCenter_new());
            validateNewCenterId(centerNew.getId(), centerFirst.getId(), centerSecond.getId());
        }

        updateCourseCenters(centerNew.getId(), centerFirst.getId(), centerSecond.getId());
        saveCentersAfterMerger(centerFirst, centerSecond);

        Merger merger = createMerger(centerFirst, centerSecond, centerNew);
        return mergerRepository.save(merger);
    }

    private void validateNewCenterId(int centerNewId, int centerFirstId, int centerSecondId) {
        if (centerNewId != centerFirstId && centerNewId != centerSecondId) {
            throw new ValidationException("New Center ID must be " + centerFirstId + " or " + centerSecondId);
        }
    }

    private void updateCourseCenters(int newCenterId, int centerFirstId, int centerSecondId) {
        Date currentDate = Date.valueOf(LocalDate.now());
        courseService.updateCenterId(newCenterId, centerFirstId, currentDate);
        courseService.updateCenterId(newCenterId, centerSecondId, currentDate);
    }

    private void saveCentersAfterMerger(Center centerFirst, Center centerSecond) {
        centerFirst.setState(false);
        centerService.save(centerFirst);
        centerSecond.setState(false);
        centerService.save(centerSecond);
    }

    private Merger createMerger(Center centerFirst, Center centerSecond, Center centerNew) {
        Merger merger = new Merger();
        merger.setCenter_first(centerFirst);
        merger.setCenter_second(centerSecond);
        merger.setCenter_new(centerNew);
        merger.setDate(Date.valueOf(LocalDate.now()));
        return merger;
    }
}
