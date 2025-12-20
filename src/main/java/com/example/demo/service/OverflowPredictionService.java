package com.example.demo.service;

import java.util.List;
import com.example.demo.model.OverflowPrediction;

public interface OverflowPredictionService {

    List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId);
}
