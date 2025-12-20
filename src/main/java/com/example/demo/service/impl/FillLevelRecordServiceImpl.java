package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class FillLevelRecordServiceImpl implements FillLevelRecordService {

    @Autowired
    private FillLevelRecordRepository fillLevelRecordRepository;

    @Override
    public List<FillLevelRecord> getAllRecords() {
        return fillLevelRecordRepository.findAll();
    }

    @Override
    public FillLevelRecord saveRecord(FillLevelRecord record) {
        // Replace getFillPercentage() with correct getter
        Double fillLevel = record.getFillLevelPercentage();

        // Example processing (you can adjust as per your logic)
        if (fillLevel > 100) {
            record.setFillLevelPercentage(100.0);
        }

        return fillLevelRecordRepository.save(record);
    }

    // Other methods...
}
