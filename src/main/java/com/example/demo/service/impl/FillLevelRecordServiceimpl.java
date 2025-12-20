package com.example.demo.service.impl;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FillLevelRecordServiceimpl implements FillLevelRecordService {

    private final FillLevelRecordRepository fillLevelRecordRepository;

    public FillLevelRecordServiceimpl(FillLevelRecordRepository fillLevelRecordRepository) {
        this.fillLevelRecordRepository = fillLevelRecordRepository;
    }

    @Override
    public List<FillLevelRecord> getRecentRecords(Long binId, int limit) {
        // Example: replace with actual repository method
        return fillLevelRecordRepository.findTopNByBinIdOrderByCreatedAtDesc(binId, limit);
    }
}
