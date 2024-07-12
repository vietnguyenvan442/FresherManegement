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
    public Merger addMerger(MergerDto mergerDto) {
        log.info("Adding merger with dto: {}", mergerDto);

        Center centerFirst = centerService.findById(mergerDto.getCenter_first_id());
        Center centerSecond = centerService.findById(mergerDto.getCenter_second_id());
        Center centerNew;

        if (mergerDto.getCenter_new().getId() == 0) {
            centerNew = centerService.save(mergerDto.getCenter_new());
            updateCourseCenters(centerNew.getId(), centerFirst.getId(), centerSecond.getId());
            centerService.deleteById(centerFirst.getId());
            centerService.deleteById(centerSecond.getId());
        } else if (mergerDto.getCenter_new().getId() == centerFirst.getId()) {
            centerNew = centerFirst;
            updateCourseCenters(centerNew.getId(), centerSecond.getId());
            centerService.deleteById(centerSecond.getId());
        } else if (mergerDto.getCenter_new().getId() == centerSecond.getId()) {
            centerNew = centerSecond;
            updateCourseCenters(centerNew.getId(), centerFirst.getId());
            centerService.deleteById(centerFirst.getId());
        } else {
            log.error("New Center ID must be {} or {}", centerFirst.getId(), centerSecond.getId());
            throw new ValidationException("New Center ID must be " + centerFirst.getId() + " or " + centerSecond.getId());
        }

        Merger merger = createMerger(centerFirst, centerSecond, centerNew);
        return mergerRepository.save(merger);
    }

    private void updateCourseCenters(int newCenterId, int... oldCenterIds) {
        Date currentDate = Date.valueOf(LocalDate.now());
        for (int oldCenterId : oldCenterIds) {
            courseService.updateCenterId(newCenterId, oldCenterId, currentDate);
        }
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
