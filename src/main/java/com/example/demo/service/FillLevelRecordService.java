package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import com.example.demo.model.FillLevelRecord;

public interface FillLevelRecordService {

    FillLevelRecord createRecord(FillLevelRecord record);

    Optional<FillLevelRecord> getRecordById(Long id);

    List<FillLevelRecord> getRecordsForBin(Long binId);
}
