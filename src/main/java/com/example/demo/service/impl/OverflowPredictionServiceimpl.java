package com.example.demo.service.impl;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverflowPredictionServiceimpl implements OverflowPredictionService {

    @Autowired
    private OverflowPredictionRepository overflowPredictionRepository;

    @Override
    public List<OverflowPrediction> getPredictionsForBin(Long binId) {
        return overflowPredictionRepository.findByBinId(binId);
    }

    @Override
    public OverflowPrediction getLatestPredictionsForZone(Long zoneId) {
        List<OverflowPrediction> list = overflowPredictionRepository.findTopByZoneIdOrderByCreatedAtDesc(zoneId);
        return list.isEmpty() ? null : list.get(0);
    }

    // Add other methods from OverflowPredictionService interface if needed
}
