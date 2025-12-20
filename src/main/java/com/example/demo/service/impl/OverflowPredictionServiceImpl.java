package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.service.OverflowPredictionService;

@Service
public class OverflowPredictionServiceImpl 
        implements OverflowPredictionService {

    @Autowired
    private OverflowPredictionRepository repo;

    @Override
    public OverflowPrediction generatePrediction(Long binId) {
        OverflowPrediction p = new OverflowPrediction();
        p.setBinId(binId);
        p.setRiskLevel("MEDIUM");
        return repo.save(p);
    }

    @Override
    public Optional<OverflowPrediction> getPredictionById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<OverflowPrediction> getPredictionsForBin(Long binId) {
        return repo.findByBinId(binId);
    }
}
