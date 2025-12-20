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

    private final OverflowPredictionRepository repository;

    @Autowired
    public OverflowPredictionServiceImpl(OverflowPredictionRepository repository) {
        this.repository = repository;
    }

    @Override
    public OverflowPrediction createPrediction(
            Bin bin,
            java.util.Date date,
            int predictedLevel,
            UsagePatternModel pattern
    ) {
        OverflowPrediction prediction = new OverflowPrediction(
                null,
                bin,
                date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate(),
                predictedLevel,
                pattern,
                Instant.now()
        );

        return repository.save(prediction);
    }
}
