package com.example.demo.service.impl;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FillLevelRecordServiceimpl implements FillLevelRecordService {

    @Autowired
    private FillLevelRecordRepository fillLevelRecordRepository;

    // Get a single record by ID
    @Override
    public FillLevelRecord getRecordById(Long id) {
        return fillLevelRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found for id: " + id));
    }

    // Get all records for a specific bin
    @Override
    public List<FillLevelRecord> getRecordsForBin(Long binId) {
        return fillLevelRecordRepository.findByBinIdOrderByCreatedAtDesc(binId);
    }

    // Get top N recent records
    @Override
    public List<FillLevelRecord> getRecentRecords(Long binId, int n) {
        return fillLevelRecordRepository.findTopNByBinIdOrderByCreatedAtDesc(binId, n);
    }
}
