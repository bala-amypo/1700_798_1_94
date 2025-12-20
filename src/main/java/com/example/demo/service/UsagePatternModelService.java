package com.example.demo.service;

import com.example.demo.model.UsagePatternModel;

import java.util.List;

public interface UsagePatternModelService {
    // Create a new usage pattern record
    UsagePatternModel create(UsagePatternModel model);

    // Get records for a specific bin
    List<UsagePatternModel> getRecordsForBin(Long binId);
}
