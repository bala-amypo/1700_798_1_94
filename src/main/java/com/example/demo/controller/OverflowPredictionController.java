package com.example.demo.controller;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/predictions")
public class OverflowPredictionController {
    private final OverflowPredictionService predictionService;

    public OverflowPredictionController(OverflowPredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping("/bin/{binId}")
    public ResponseEntity<OverflowPrediction> generatePrediction(@PathVariable Long binId) {
        return ResponseEntity.ok(predictionService.generatePrediction(binId));
    }

    @GetMapping("/zone/{zoneId}")
    public ResponseEntity<List<OverflowPrediction>> getLatestPredictionsForZone(@PathVariable Long zoneId) {
        return ResponseEntity.ok(predictionService.getLatestPredictionsForZone(zoneId));
    }
}