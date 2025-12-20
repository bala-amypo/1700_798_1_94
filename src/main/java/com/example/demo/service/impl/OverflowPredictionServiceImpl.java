package com.example.demo.service.impl;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final OverflowPredictionRepository overflowPredictionRepository;

    public OverflowPredictionServiceImpl(OverflowPredictionRepository overflowPredictionRepository) {
        this.overflowPredictionRepository = overflowPredictionRepository;
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        // Example: replace with actual repository method
        return overflowPredictionRepository.findLatestByZone(zoneId);
    }
}
