package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import com.example.demo.model.OverflowPrediction;

public interface OverflowPredictionService {

    OverflowPrediction generatePrediction(Long binId);

    Optional<OverflowPrediction> getPredictionById(Long id);

    List<OverflowPrediction> getPredictionsForBin(Long binId);
}
