package com.example.demo.service.impl;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FillLevelRecordServiceimpl implements FillLevelRecordService {

    @Autowired
    private FillLevelRecordRepository fillLevelRecordRepository;

    @Override
    public FillLevelRecord getRecordById(Long id) {
        return fillLevelRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found for id: " + id));
    }

    @Override
    public List<FillLevelRecord> getRecentRecords(Long binId, int n) {
        Pageable topN = PageRequest.of(0, n);
        return fillLevelRecordRepository.findByBinIdOrderByCreatedAtDesc(binId, topN);
    }

    // Add other methods from FillLevelRecordService interface if needed
}
