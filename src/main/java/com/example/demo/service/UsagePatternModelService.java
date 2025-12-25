package com.example.demo.service;

import com.example.demo.model.UsagePatternModel;

public interface UsagePatternModelService {
    UsagePatternModel createModel(UsagePatternModel model);
    UsagePatternModel getModelForBin(Long binId);
    UsagePatternModel updateModel(Long id, UsagePatternModel update);
}