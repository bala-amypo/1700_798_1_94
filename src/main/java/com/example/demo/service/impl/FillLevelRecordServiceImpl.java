package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;

import java.sql.Timestamp;
import java.time.Instant;
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
            throw new BadRequestException("fillPercentage must be between 0 and 100");
        }

        if (record.getRecordedAt().after(Timestamp.from(Instant.now()))) {
            throw new BadRequestException("recordedAt cannot be in the future");
        }

        record = new FillLevelRecord(
                bin,
                record.getFillPercentage(),
                record.getRecordedAt(),
                record.getIsWeekend()
        );

        return recordRepository.save(record);
    }

    @Override
    public List<FillLevelRecord> getRecordsForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("bin not found"));
        return recordRepository.findByBinOrderByRecordedAtDesc(bin);
    }

    @Override
    public FillLevelRecord getRecordById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("record not found"));
    }

    @Override
    public List<FillLevelRecord> getRecentRecords(Long binId, int limit) {
        List<FillLevelRecord> records = getRecordsForBin(binId);
        return records.stream().limit(limit).toList();
    }
}
