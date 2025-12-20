package com.example.demo.service.impl;

import com.example.demo.service.OverflowPredictionService;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.exception.*;
import java.sql.Timestamp;
import java.util.*;

public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final BinRepository binRepository;
    private final FillLevelRecordRepository recordRepository;
    private final UsagePatternModelRepository modelRepository;
    private final OverflowPredictionRepository predictionRepository;
    private final ZoneRepository zoneRepository;

    public OverflowPredictionServiceImpl(
            BinRepository binRepository,
            FillLevelRecordRepository recordRepository,
            UsagePatternModelRepository modelRepository,
            OverflowPredictionRepository predictionRepository,
            ZoneRepository zoneRepository) {

        this.binRepository = binRepository;
        this.recordRepository = recordRepository;
        this.modelRepository = modelRepository;
        this.predictionRepository = predictionRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public OverflowPrediction generatePrediction(Long binId) {

        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("bin not found"));

        FillLevelRecord record = recordRepository
                .findTop1ByBinOrderByRecordedAtDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("fill record not found"));

        UsagePatternModel model = modelRepository
                .findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("model not found"));

        double dailyIncrease = record.getIsWeekend()
                ? model.getAvgDailyIncreaseWeekend()
                : model.getAvgDailyIncreaseWeekday();

        int daysUntilFull = dailyIncrease == 0
                ? 0
                : (int) Math.ceil((100 - record.getFillPercentage()) / dailyIncrease);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, daysUntilFull);

        OverflowPrediction prediction = new OverflowPrediction(
                bin,
                cal.getTime(),
                daysUntilFull,
                model,
                new Timestamp(System.currentTimeMillis())
        );

        return predictionRepository.save(prediction);
    }

    @Override
    public OverflowPrediction getPredictionById(Long id) {
        return predictionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("prediction not found"));
    }

    @Override
    public List<OverflowPrediction> getPredictionsForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("bin not found"));
        return predictionRepository.findAll()
                .stream().filter(p -> p.getBin().equals(bin)).toList();
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("zone not found"));
        return predictionRepository.findLatestPredictionsForZone(zone);
    }
}
