package com.example.demo.service;

import com.example.demo.model.UsagePatternModel;

import java.util.List;

public interface UsagePatternModelService {

    // Create a new usage pattern record
    UsagePatternModel createModel(UsagePatternModel model);

    // Update a usage pattern record
    UsagePatternModel updateModel(Long id, UsagePatternModel model);

    // Get all usage pattern records for a bin
    List<UsagePatternModel> getModelForBin(Long binId);

    // Get all usage pattern records
    List<UsagePatternModel> getAllModels();
}
