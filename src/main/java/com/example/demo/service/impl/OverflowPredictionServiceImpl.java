package com.example.demo.service.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.service.OverflowPredictionService;
@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    @Autowired
    private OverflowPredictionRepository repo;

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        return repo.findByBin_Zone_Id(zoneId);
    }
}
