package com.example.demo.service;

import com.example.demo.model.UsagePatternModel;

import java.util.Optional;

public interface UsagePatternModelService {
    UsagePatternModel createModel(UsagePatternModel model);
    UsagePatternModel getModelById(Long id);
    Optional<UsagePatternModel> getModelForBin(Long binId);
    UsagePatternModel updateModel(Long id, UsagePatternModel model);
    void deleteModel(Long id);
}