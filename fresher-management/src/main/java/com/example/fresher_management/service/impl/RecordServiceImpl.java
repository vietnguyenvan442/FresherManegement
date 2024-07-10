package com.example.fresher_management.service.impl;

import com.example.fresher_management.dto.RecordDto;
import com.example.fresher_management.entity.Course;
import com.example.fresher_management.entity.Fresher;
import com.example.fresher_management.entity.Record;
import com.example.fresher_management.exception.CourseEndedException;
import com.example.fresher_management.exception.FresherAlreadyExistsCourseException;
import com.example.fresher_management.repository.RecordRepository;
import com.example.fresher_management.service.CourseService;
import com.example.fresher_management.service.FresherService;
import com.example.fresher_management.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class RecordServiceImpl implements RecordService {

    @Autowired
    private CourseService courseService;

    @Autowired
    private FresherService fresherService;

    @Autowired
    private RecordRepository recordRepository;

    @Override
    @Transactional
    public Record addRecord(RecordDto recordDto) {
        log.info("Adding record with dto: {}", recordDto);

        Fresher fresher = fresherService.findById(recordDto.getFresher_id());
        Course course = courseService.findById(recordDto.getCenter_id());

        validateFresherInCourse(course.getId(), fresher.getId());

        updateLatestRecordsForFresher(fresher.getId());

        Record record = createNewRecord(fresher, course);
        return recordRepository.save(record);
    }

    private void validateFresherInCourse(int courseId, int fresherId) {
        if (!checkUniqueFresherToCourse(courseId, fresherId)) {
            throw new FresherAlreadyExistsCourseException("Fresher ID: " + fresherId + " already exists in Course ID: " + courseId);
        }

        if (courseService.checkCourseEnded(courseId)) {
            throw new CourseEndedException("Course has ended");
        }
    }

    private void updateLatestRecordsForFresher(int fresherId) {
        List<Record> latestRecords = recordRepository.findLateActiveRecordsByFresherId(fresherId);
        if (!latestRecords.isEmpty()) {
            latestRecords.forEach(record -> {
                record.setEnd_time(Date.valueOf(LocalDate.now()));
                recordRepository.save(record);
            });
        }
    }

    private Record createNewRecord(Fresher fresher, Course course) {
        Record record = new Record();
        record.setFresher(fresher);
        record.setCourse(course);
        record.setRole(fresher.getRole());
        record.setStart_time(Date.valueOf(LocalDate.now()));
        return record;
    }

    @Transactional
    public boolean checkUniqueFresherToCourse(int courseId, int fresherId) {
        log.info("Checking if fresher ID: {} is unique to course ID: {}", fresherId, courseId);
        List<Record> records = recordRepository.findFresherToCourse(courseId, fresherId);
        return records.isEmpty();
    }
}
