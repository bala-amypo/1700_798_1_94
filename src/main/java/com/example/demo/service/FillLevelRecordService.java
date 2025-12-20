package com.example.demo.service;

import java.util.List;
import com.example.demo.model.FillLevelRecord;

public interface FillLevelRecordService {

    List<FillLevelRecord> getRecentRecords(Long binId, int limit);

    FillLevelRecord save(FillLevelRecord record);
}
