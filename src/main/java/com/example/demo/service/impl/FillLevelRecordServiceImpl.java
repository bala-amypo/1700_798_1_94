package com.example.demo.service.impl;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;
@Service
public class FillLevelRecordServiceImpl implements FillLevelRecordService {

    @Autowired
    private FillLevelRecordRepository repo;

    @Override
    public List<FillLevelRecord> getRecentRecords(Long binId, int limit) {
        return repo.findTopByBinIdOrderByRecordedAtDesc(binId, limit);
    }

    @Override
    public FillLevelRecord save(FillLevelRecord record) {
        return repo.save(record);
    }
}
