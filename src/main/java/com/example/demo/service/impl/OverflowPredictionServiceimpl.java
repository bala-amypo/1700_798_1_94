package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
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
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id: " + binId));
        
        if (!bin.getActive()) {
            throw new BadRequestException("Cannot generate prediction for inactive bin");
        }
        
        // Get latest fill level record
        FillLevelRecord latestRecord = recordRepository.findTop1ByBinOrderByRecordedAtDesc(bin)
                .orElseThrow(() -> new BadRequestException("No fill level records found for bin"));
        
        // Get latest usage pattern model
        UsagePatternModel model = modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new BadRequestException("No usage pattern model found for bin"));
        
        // Calculate days until full
        double currentFill = latestRecord.getFillPercentage();
        double capacity = bin.getCapacityLiters();
        double remainingCapacityPercentage = 100 - currentFill;
        
        // Use weekday increase for calculation (simplified)
        double dailyIncrease = model.getAvgDailyIncreaseWeekday();
        if (dailyIncrease <= 0) {
            throw new BadRequestException("Cannot generate prediction with zero or negative daily increase");
        }
        
        int daysUntilFull = (int) Math.ceil(remainingCapacityPercentage / dailyIncrease);
        
        if (daysUntilFull < 0) {
            daysUntilFull = 0; // Already full or overflowing
        }
        
        // Create prediction
        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        prediction.setModelUsed(model);
        prediction.setDaysUntilFull(daysUntilFull);
        prediction.setPredictedFullDate(LocalDate.now().plusDays(daysUntilFull));
        prediction.setPredictedAt(LocalDateTime.now());
        
        return predictionRepository.save(prediction);
    }
    
    @Override
    @Transactional(readOnly = true)
    public OverflowPrediction getPredictionById(Long id) {
        return predictionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Overflow prediction not found with id: " + id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<OverflowPrediction> getPredictionsForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        
        return predictionRepository.findByBinOrderByPredictedAtDesc(bin);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        
        return predictionRepository.findLatestPredictionsForZone(zone);
    }
    
    @Override
    @Transactional(readOnly = true)
    public OverflowPrediction getLatestPredictionForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        
        return predictionRepository.findTop1ByBinOrderByPredictedAtDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("No predictions found for bin"));
    }
    
    @Override
    public void deletePrediction(Long id) {
        OverflowPrediction prediction = getPredictionById(id);
        predictionRepository.delete(prediction);
    }
}