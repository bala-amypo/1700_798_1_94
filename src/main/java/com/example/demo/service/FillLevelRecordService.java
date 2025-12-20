package com.example.demo.service;

import java.util.List;
import com.example.demo.model.FillLevelRecord;

public interface FillLevelRecordService {

    FillLevelRecord createRecord(FillLevelRecord record);

    FillLevelRecord getRecordById(Long id);

    List<FillLevelRecord> getRecordsForBin(Long binId);

    // ⭐ ADD THIS
    List<FillLevelRecord> getRecentRecords(Long binId, int limit);
}
