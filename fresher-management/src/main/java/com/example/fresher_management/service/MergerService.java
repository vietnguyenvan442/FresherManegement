package com.example.fresher_management.service;

import com.example.fresher_management.dto.MergerDto;
import com.example.fresher_management.dto.MergerFirstToSecondDto;
import com.example.fresher_management.entity.Center;
import com.example.fresher_management.entity.Merger;
import com.example.fresher_management.repository.MergerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;

@Service
public class MergerService {
    @Autowired
    private MergerRepository mergerRepository;

    @Autowired
    private CenterService centerService;

    @Transactional
    public Merger addMerger(MergerDto mergerDto){
        Center center_first = centerService.findById(mergerDto.getCenter_first_id());
        if (center_first == null) throw new RuntimeException("Center first not found");

        Center center_second = centerService.findById(mergerDto.getCenter_second_id());
        if (center_second == null) throw new RuntimeException("Center second not found");

        Center center_new = centerService.addCenter(mergerDto.getCenter_new());

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

    @Transactional
    public Merger addMergerFirstToSecond(MergerFirstToSecondDto mergerFirstToSecondDto){
        Center center_first = centerService.findById(mergerFirstToSecondDto.getCenter_first_id());
        if (center_first == null) throw new RuntimeException("Center first not found");

        Center center_second = centerService.findById(mergerFirstToSecondDto.getCenter_second_id());
        if (center_second == null) throw new RuntimeException("Center second not found");

        Merger merger = new Merger();
        merger.setCenter_first(center_first);
        merger.setCenter_second(center_second);
        merger.setCenter_new(center_second);
        merger.setDate(Date.valueOf(LocalDate.now()));

        center_first.setState(false);
        centerService.save(center_first);

        return mergerRepository.save(merger);
    }
}
