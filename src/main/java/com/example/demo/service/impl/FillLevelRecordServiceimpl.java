package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FillLevelRecordServiceImpl implements FillLevelRecordService {
    
    private final FillLevelRecordRepository recordRepository;
    private final BinRepository binRepository;
    
    public FillLevelRecordServiceImpl(FillLevelRecordRepository recordRepository, BinRepository binRepository) {
        this.recordRepository = recordRepository;
        this.binRepository = binRepository;
    }
    
    @Override
    public FillLevelRecord createRecord(FillLevelRecord record) {
        validateRecord(record);
        
        Bin bin = binRepository.findById(record.getBin().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id: " + record.getBin().getId()));
        
        if (!bin.getActive()) {
            throw new BadRequestException("Cannot add record to inactive bin");
        }
        
        record.setBin(bin);
        
        if (record.getRecordedAt() == null) {
            record.setRecordedAt(LocalDateTime.now());
        }
        
        return recordRepository.save(record);
    }
    
    @Override
    @Transactional(readOnly = true)
    public FillLevelRecord getRecordById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fill level record not found with id: " + id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<FillLevelRecord> getAllRecords() {
        return recordRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<FillLevelRecord> getRecentRecords(Long binId, int limit) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        
        List<FillLevelRecord> allRecords = recordRepository.findByBinOrderByRecordedAtDesc(bin);
        
        return allRecords.stream()
                .limit(limit)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<FillLevelRecord> getRecordsByBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        
        return recordRepository.findByBinOrderByRecordedAtDesc(bin);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<FillLevelRecord> getLatestRecordForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        
        return recordRepository.findTop1ByBinOrderByRecordedAtDesc(bin);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<FillLevelRecord> getRecordsBetweenDates(Long binId, LocalDateTime start, LocalDateTime end) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        
        return recordRepository.findByBinAndRecordedAtBetween(bin, start, end);
    }
    
    private void validateRecord(FillLevelRecord record) {
        if (record.getFillPercentage() == null || record.getFillPercentage() < 0 || record.getFillPercentage() > 100) {
            throw new BadRequestException("Fill percentage must be between 0 and 100");
        }
        
        if (record.getBin() == null || record.getBin().getId() == null) {
            throw new BadRequestException("Bin is required for fill record");
        }
        
        // Check if recordedAt is in the future
        if (record.getRecordedAt() != null && record.getRecordedAt().isAfter(LocalDateTime.now())) {
            throw new BadRequestException("Record date/time cannot be in the future");
        }
    }
}