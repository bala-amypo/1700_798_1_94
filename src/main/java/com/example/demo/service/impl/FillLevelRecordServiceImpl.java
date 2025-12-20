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

    private final FillLevelRecordRepository repository;

    @Autowired
    public FillLevelRecordServiceImpl(FillLevelRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FillLevelRecord> getRecentRecords(Instant afterTime) {
        return repository.findAll()
                .stream()
                .filter(r -> r.getRecordedAt().isAfter(afterTime))
                .toList();
    }

    @Override
    public FillLevelRecord save(FillLevelRecord record) {
        return repository.save(record);
    }
}
