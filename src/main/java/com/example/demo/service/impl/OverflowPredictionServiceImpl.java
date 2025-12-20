package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    @Autowired
    private OverflowPredictionRepository overflowPredictionRepository;

    @Override
    public OverflowPrediction predictOverflow(FillLevelRecord latestRecord, UsagePatternModel pattern) {
        // Replace getFillPercentage() with correct getter
        int fillLevel = latestRecord.getFillLevelPercentage().intValue();

        // Create OverflowPrediction using default constructor + setters
        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(latestRecord.getBin());  // make sure FillLevelRecord has getBin()
        prediction.setPredictedFillLevel(fillLevel);
        prediction.setPredictedTime(new Timestamp(new Date().getTime()));
        prediction.setUsagePattern(pattern); // if field exists in OverflowPrediction

        return overflowPredictionRepository.save(prediction);
    }

    // Other methods...
}
