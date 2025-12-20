package com.example.demo.service.impl;

import com.example.demo.service.FillLevelRecordService;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.exception.*;
import java.sql.Timestamp;
import java.util.List;

public class FillLevelRecordServiceImpl implements FillLevelRecordService {

    private final FillLevelRecordRepository recordRepository;
    private final BinRepository binRepository;

    public FillLevelRecordServiceImpl(FillLevelRecordRepository recordRepository,
                                      BinRepository binRepository) {
        this.recordRepository = recordRepository;
        this.binRepository = binRepository;
    }

    @Override
    public FillLevelRecord createRecord(FillLevelRecord record) {

        Bin bin = binRepository.findById(record.getBin().getId())
                .orElseThrow(() -> new ResourceNotFoundException("bin not found"));

        if (!bin.getActive()) {
            throw new BadRequestException("bin is inactive");
        }

        if (record.getFillPercentage() < 0 || record.getFillPercentage() > 100) {
            throw new BadRequestException("fill percentage must be between 0 and 100");
        }

        if (record.getRecordedAt().after(new Timestamp(System.currentTimeMillis()))) {
            throw new BadRequestException("recordedAt cannot be in the future");
        }

        record.setBin(bin);
        return recordRepository.save(record);
    }

    @Override
    public FillLevelRecord getRecordById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("record not found"));
    }

    @Override
    public List<FillLevelRecord> getRecordsForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("bin not found"));
        return recordRepository.findByBinOrderByRecordedAtDesc(bin);
    }

    @Override
    public List<FillLevelRecord> getRecentRecords(Long binId, int limit) {
        List<FillLevelRecord> records = getRecordsForBin(binId);
        return records.subList(0, Math.min(limit, records.size()));
    }
}
