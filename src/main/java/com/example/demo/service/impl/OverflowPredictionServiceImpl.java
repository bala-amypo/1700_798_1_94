package com.example.demo.service.impl;

import com.example.demo.exception.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.*;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {
    private final BinRepository binRepository;
    private final FillLevelRecordRepository recordRepository;
    private final UsagePatternModelRepository modelRepository;
    private final OverflowPredictionRepository predictionRepository;
    private final ZoneRepository zoneRepository;

    public OverflowPredictionServiceImpl(BinRepository binRepo, FillLevelRecordRepository recRepo, 
                                         UsagePatternModelRepository modelRepo, OverflowPredictionRepository predRepo, 
                                         ZoneRepository zoneRepo) {
        this.binRepository = binRepo;
        this.recordRepository = recRepo;
        this.modelRepository = modelRepo;
        this.predictionRepository = predRepo;
        this.zoneRepository = zoneRepo;
    }

    @Override
    public OverflowPrediction generatePrediction(Long binId) {
        Bin bin = binRepository.findById(binId).orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        
        FillLevelRecord latest = recordRepository.findTop1ByBinOrderByRecordedAtDesc(bin)
                .orElseThrow(() -> new BadRequestException("No fill records available for prediction"));
        
        UsagePatternModel model = modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new BadRequestException("No usage model available for prediction"));

        double remainingCapacity = 100.0 - latest.getFillPercentage();
        // Calculation: uses weighted average or weekday default
        double dailyRate = model.getAvgDailyIncreaseWeekday();
        if (dailyRate <= 0) dailyRate = 1.0; // Avoid division by zero

        int daysUntilFull = (int) Math.ceil(remainingCapacity / dailyRate);
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, daysUntilFull);

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
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId).orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        return predictionRepository.findLatestPredictionsForZone(zone);
    }
}