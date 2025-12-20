package com.example.demo.controller;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/models")
public class UsagePatternModelController {
    private final UsagePatternModelService modelService;

    public UsagePatternModelController(UsagePatternModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping
    public ResponseEntity<UsagePatternModel> createModel(@RequestBody UsagePatternModel model) {
        return ResponseEntity.ok(modelService.createModel(model));
    }

    @GetMapping("/bin/{binId}")
    public ResponseEntity<UsagePatternModel> getByBin(@PathVariable Long binId) {
        return ResponseEntity.ok(modelService.getModelForBin(binId));
    }
}