package com.example.fresher_management.service;

import com.example.fresher_management.dto.RecordDto;
import com.example.fresher_management.entity.*;
import com.example.fresher_management.entity.Record;
import com.example.fresher_management.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;

@Service
public class RecordService {

    @Autowired
    private CenterRepository centerRepository;

    @Autowired
    private FresherRepository fresherRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Transactional
    public Record addRecord(RecordDto recordDto) {
        Fresher fresher = fresherRepository.findById(recordDto.getFresher_id())
                .orElseThrow(() -> new RuntimeException("Fresher not found"));
        Center center = centerRepository.findById(recordDto.getCenter_id())
                .orElseThrow(() -> new RuntimeException("Center not found"));

        Record record = new Record();
        record.setFresher(fresher);
        record.setCenter(center);
        record.setPosition(fresher.getPosition());
        record.setStart_time(Date.valueOf(LocalDate.now()));

        return recordRepository.save(record);
    }
}
