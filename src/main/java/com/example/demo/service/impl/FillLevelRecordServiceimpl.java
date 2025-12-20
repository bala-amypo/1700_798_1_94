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
    public List<FillLevelRecord> getRecordsForBin(Long binId) {
        Pageable topN = PageRequest.of(0, 10); // top 10 records
        return fillLevelRecordRepository.findByBinIdOrderByCreatedAtDesc(binId, topN);
    }
}
