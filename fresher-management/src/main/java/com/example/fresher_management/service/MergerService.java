package com.example.fresher_management.service;

import com.example.fresher_management.dto.MergerDto;
import com.example.fresher_management.entity.Merger;

public interface MergerService {
    public Merger addMerger(MergerDto mergerDto);
}
