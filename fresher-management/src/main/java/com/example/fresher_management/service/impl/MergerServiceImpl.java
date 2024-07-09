package com.example.fresher_management.service.impl;

import com.example.fresher_management.dto.MergerDto;
import com.example.fresher_management.entity.Center;
import com.example.fresher_management.entity.Course;
import com.example.fresher_management.entity.Merger;
import com.example.fresher_management.exception.ValidationException;
import com.example.fresher_management.repository.MergerRepository;
import com.example.fresher_management.service.CenterService;
import com.example.fresher_management.service.CourseService;
import com.example.fresher_management.service.MergerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class MergerServiceImpl implements MergerService {
    @Autowired
    private MergerRepository mergerRepository;

    @Autowired
    private CenterService centerService;

    @Autowired
    private CourseService courseService;

    @Transactional
    public Merger addMerger(MergerDto mergerDto){
        Center center_first = centerService.findById(mergerDto.getCenter_first_id());
        Center center_second = centerService.findById(mergerDto.getCenter_second_id());

        Center center_new = new Center();
        if (mergerDto.getCenter_new().getId() == 0){
            center_new = centerService.save(mergerDto.getCenter_new());

            courseService.updateCenterId(center_new.getId(), center_first.getId(), Date.valueOf(LocalDate.now()));
            courseService.updateCenterId(center_new.getId(), center_second.getId(), Date.valueOf(LocalDate.now()));
        }
         else {
             center_new = centerService.updateById(mergerDto.getCenter_new().getId(), mergerDto.getCenter_new());

             if (center_new.getId() == center_first.getId()) courseService.updateCenterId(center_new.getId(), center_second.getId(), Date.valueOf(LocalDate.now()));
             else if (center_new.getId() == center_second.getId()) courseService.updateCenterId(center_new.getId(), center_first.getId(), Date.valueOf(LocalDate.now()));
             else throw new ValidationException("New Center ID must be " + center_first.getId() + " or " + center_second.getId());
        }

        Merger merger = new Merger();
        merger.setCenter_first(center_first);
        merger.setCenter_second(center_second);
        merger.setCenter_new(center_new);
        merger.setDate(Date.valueOf(LocalDate.now()));

        center_first.setState(false);
        centerService.save(center_first);
        center_second.setState(false);
        centerService.save(center_second);

        return mergerRepository.save(merger);
    }
}
