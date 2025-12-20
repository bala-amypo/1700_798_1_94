package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.service.OverflowPredictionService;

@RestController
@RequestMapping("/api/predictions")
public class OverflowPredictionController {

    private final OverflowPredictionService predictionService;

    @Autowired
    public OverflowPredictionController(OverflowPredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping("/generate/{binId}")
    public OverflowPrediction generatePrediction(@PathVariable Long binId) {
        return predictionService.generatePrediction(binId);
    }

    @GetMapping("/{id}")
    public OverflowPrediction getPrediction(@PathVariable Long id) {
        return predictionService.getPredictionById(id);
    }

    @GetMapping("/bin/{binId}")
    public List<OverflowPrediction> getPredictionsForBin(@PathVariable Long binId) {
        return predictionService.getPredictionsForBin(binId);
    }

    @GetMapping("/zone/{zoneId}/latest")
    public List<OverflowPrediction> getLatestPredictionsForZone(
            @PathVariable Long zoneId) {
        return predictionService.getLatestPredictionsForZone(zoneId);
    }
}
