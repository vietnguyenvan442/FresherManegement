package com.example.fresher_management.service.impl;

import com.example.fresher_management.dto.RecordDto;
import com.example.fresher_management.entity.*;
import com.example.fresher_management.entity.Record;
import com.example.fresher_management.exception.CourseEndedException;
import com.example.fresher_management.exception.FresherAlreadyExistsCourseException;
import com.example.fresher_management.repository.*;
import com.example.fresher_management.service.CourseService;
import com.example.fresher_management.service.FresherService;
import com.example.fresher_management.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private CourseService courseService;

    @Autowired
    private FresherService fresherService;

    @Autowired
    private RecordRepository recordRepository;

    @Transactional
    public Record addRecord(RecordDto recordDto) {
        Fresher fresher = fresherService.findById(recordDto.getFresher_id());
        Course course = courseService.findById(recordDto.getCenter_id());

        if (!checkUniqueFresherToCourse(course.getId(), fresher.getId())){
            throw new FresherAlreadyExistsCourseException("Fresher ID: " + fresher.getId() + " Already Exists Course ID: " + course.getId());
        }

        if (courseService.checkCourseEnded(course.getId())) throw new CourseEndedException("Course has ended");

        List<Record> latestRecord = recordRepository.findLateActiveRecordsByFresherId(fresher.getId());
        if (!latestRecord.isEmpty()) {
            for (Record record: latestRecord){
                record.setEnd_time(Date.valueOf(LocalDate.now()));
                recordRepository.save(record);
            }
        }

        Record record = new Record();
        record.setFresher(fresher);
        record.setCourse(course);
        record.setRole(fresher.getRole());
        record.setStart_time(Date.valueOf(LocalDate.now()));

        return recordRepository.save(record);
    }

    @Transactional
    public boolean checkUniqueFresherToCourse(int course_id, int fresher_id){
        List<Record> records = recordRepository.findFresherToCourse(course_id, fresher_id);
        return !records.isEmpty();
    }

}
