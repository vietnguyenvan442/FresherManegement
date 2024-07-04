package com.example.fresher_management.service;

import com.example.fresher_management.dto.RecordDto;
import com.example.fresher_management.entity.Record;

public interface RecordService {
    public Record addRecord(RecordDto recordDto);
}
