package com.example.fresher_management.service;

import com.example.fresher_management.dto.RecordDto;
import com.example.fresher_management.entity.Record;

<<<<<<< HEAD
public interface RecordService {
    public Record addRecord(RecordDto recordDto);
=======
import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    @Autowired
    private CenterService centerService;

    @Autowired
    private FresherService fresherService;

    @Autowired
    private RecordRepository recordRepository;

    @Transactional
    public Record addRecord(RecordDto recordDto) {
        Fresher fresher = fresherService.findById(recordDto.getFresher_id());
        if (fresher == null) {
            throw new RuntimeException("Không tìm thấy Fresher");
        }

        Center center = centerService.findById(recordDto.getCenter_id());
        if (center == null) {
            throw new RuntimeException("Không tìm thấy Center");
        }

        List<Record> latestRecord = recordRepository.findLatestActiveRecordsByFresherId(fresher.getId());
        if (!latestRecord.isEmpty() && latestRecord.get(0).getEnd_time() == null) {
            latestRecord.get(0).setEnd_time(Date.valueOf(LocalDate.now()));
            recordRepository.save(latestRecord.get(0));
        }

        Record record = new Record();
        record.setFresher(fresher);
        record.setCenter(center);
        record.setPosition(fresher.getPosition());
        record.setStart_time(Date.valueOf(LocalDate.now()));

        return recordRepository.save(record);
    }

>>>>>>> 9f1e8361f333996e63834602545a5a7b55b43b6f
}
