package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

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

        FillLevelRecord latestRecord = recordRepository
                .findTop1ByBinOrderByRecordedAtDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("record not found"));

        UsagePatternModel model = modelRepository
                .findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("model not found"));

        double dailyIncrease = latestRecord.getIsWeekend()
                ? model.getAvgDailyIncreaseWeekend()
                : model.getAvgDailyIncreaseWeekday();

        if (dailyIncrease <= 0) {
            throw new BadRequestException("daily increase must be positive");
        }

        double remaining = 100 - latestRecord.getFillPercentage();
        int daysUntilFull = (int) Math.ceil(remaining / dailyIncrease);

        Date predictedDate = new Date(
                System.currentTimeMillis() + (long) daysUntilFull * 24 * 60 * 60 * 1000
        );

        OverflowPrediction prediction = new OverflowPrediction(
                bin,
                predictedDate,
                daysUntilFull,
                model,
                Timestamp.from(Instant.now())
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
                .stream()
                .filter(p -> p.getBin().getId().equals(bin.getId()))
                .toList();
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("zone not found"));
        return predictionRepository.findLatestPredictionsForZone(zone);
    }
}
