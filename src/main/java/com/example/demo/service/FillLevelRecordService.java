package com.example.demo.service;

import com.example.demo.model.FillLevelRecord;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FillLevelRecordService {
    FillLevelRecord createRecord(FillLevelRecord record);
    FillLevelRecord getRecordById(Long id);
    List<FillLevelRecord> getAllRecords();
    List<FillLevelRecord> getRecentRecords(Long binId, int limit);
    List<FillLevelRecord> getRecordsByBin(Long binId);
    Optional<FillLevelRecord> getLatestRecordForBin(Long binId);
    List<FillLevelRecord> getRecordsBetweenDates(Long binId, LocalDateTime start, LocalDateTime end);
}