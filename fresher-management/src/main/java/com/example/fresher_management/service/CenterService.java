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
import java.util.List;
import java.util.Optional;

@Service
public class CenterService {

    @Autowired
    private CenterRepository centerRepository;

    @Autowired
    private FresherRepository fresherRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Transactional
    public List<Center> getAllCenters() {
        return centerRepository.findAll();
    }

    @Transactional
    public Center addCenter(Center center) {
        // Kiểm tra nếu Area đã tồn tại
        if (center.getArea() != null) {
            Area existingArea = areaRepository.findById(center.getArea().getId()).orElse(null);
            if (existingArea != null) {
                center.setArea(existingArea);
            } else {
                areaRepository.save(center.getArea());
            }
        }

        // Kiểm tra nếu Manager đã tồn tại
        if (center.getManager() != null) {
            Manager existingManager = managerRepository.findById(center.getManager().getId()).orElse(null);
            if (existingManager != null) {
                center.setManager(existingManager);
            } else {
                managerRepository.save(center.getManager());
            }
        }

        return centerRepository.save(center);
    }

    @Transactional
    public Center updateCenter(int id, Center updatedCenter) {
        Optional<Center> optionalCenter = centerRepository.findById(id);
        if (optionalCenter.isPresent()) {
            Center existingCenter = optionalCenter.get();

            // Cập nhật thông tin của Center
            existingCenter.setName(updatedCenter.getName());
            existingCenter.setSdt(updatedCenter.getSdt());
            existingCenter.setAddress(updatedCenter.getAddress());
            existingCenter.setEmail(updatedCenter.getEmail());
            existingCenter.setDescription(updatedCenter.getDescription());
            existingCenter.setState(updatedCenter.isState());

            // Kiểm tra nếu Area đã tồn tại
            if (updatedCenter.getArea() != null) {
                Area existingArea = areaRepository.findById(updatedCenter.getArea().getId()).orElse(null);
                if (existingArea != null) {
                    existingCenter.setArea(existingArea);
                } else {
                    areaRepository.save(updatedCenter.getArea());
                    existingCenter.setArea(updatedCenter.getArea());
                }
            }

            // Kiểm tra nếu Manager đã tồn tại
            if (updatedCenter.getManager() != null) {
                Manager existingManager = managerRepository.findById(updatedCenter.getManager().getId()).orElse(null);
                if (existingManager != null) {
                    existingCenter.setManager(existingManager);
                } else {
                    managerRepository.save(updatedCenter.getManager());
                    existingCenter.setManager(updatedCenter.getManager());
                }
            }

            return centerRepository.save(existingCenter);
        } else {
            throw new RuntimeException("Center not found with id " + id);
        }
    }

    @Transactional
    public void deleteCenter(int id) {
        Optional<Center> optionalCenter = centerRepository.findById(id);
        if (optionalCenter.isPresent()) {
            Center existingCenter = optionalCenter.get();
            existingCenter.setState(false);
            centerRepository.save(existingCenter);
        } else {
            throw new RuntimeException("Center not found with id " + id);
        }
    }

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
