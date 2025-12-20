package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;

import java.sql.Timestamp;
import java.util.*;

public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final BinRepository binRepository;
    private final FillLevelRecordRepository recordRepository;
    private final UsagePatternModelRepository modelRepository;
    private final OverflowPredictionRepository predictionRepository;

    public OverflowPredictionServiceImpl(
            BinRepository binRepository,
            FillLevelRecordRepository recordRepository,
            UsagePatternModelRepository modelRepository,
            OverflowPredictionRepository predictionRepository) {
        this.binRepository = binRepository;
        this.recordRepository = recordRepository;
        this.modelRepository = modelRepository;
        this.predictionRepository = predictionRepository;
    }

    public OverflowPrediction generatePrediction(Long binId) {

        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("bin not found"));

        FillLevelRecord latestRecord =
                recordRepository.findTop1ByBinOrderByRecordedAtDesc(bin)
                        .orElseThrow(() -> new ResourceNotFoundException("no records"));

        UsagePatternModel model =
                modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
                        .orElseThrow(() -> new ResourceNotFoundException("no model"));

        double remaining = 100 - latestRecord.getFillPercentage();
        double daily = model.getAvgDailyIncreaseWeekday();
        int days = (int) Math.ceil(remaining / daily);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);

        OverflowPrediction prediction = new OverflowPrediction(
                bin,
                cal.getTime(),
                days,
                model,
                new Timestamp(System.currentTimeMillis())
        );

        return predictionRepository.save(prediction);
    }

    public OverflowPrediction getPredictionById(Long id) {
        return predictionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("prediction not found"));
    }

    public List<OverflowPrediction> getPredictionsForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("bin not found"));
        return predictionRepository.findAll();
    }
}
