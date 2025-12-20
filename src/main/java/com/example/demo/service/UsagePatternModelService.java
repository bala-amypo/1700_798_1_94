package com.example.demo.service;

import java.util.List;
import com.example.demo.model.UsagePatternModel;

public interface UsagePatternModelService {

    UsagePatternModel save(UsagePatternModel model);

    List<UsagePatternModel> getAllModels();
}
