package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

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
    public FillLevelRecord createRecord(FillLevelRecord record) {
        return repo.save(record);
    }

    @Override
    public Optional<FillLevelRecord> getRecordById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<FillLevelRecord> getRecordsForBin(Long binId) {
        return repo.findByBinId(binId);
    }
}
