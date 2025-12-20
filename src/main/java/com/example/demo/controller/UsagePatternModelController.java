package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;

@RestController
@RequestMapping("/api/models")
public class UsagePatternModelController {

    private final UsagePatternModelService modelService;

    @Autowired
    public UsagePatternModelController(UsagePatternModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping
    public UsagePatternModel createModel(@RequestBody UsagePatternModel model) {
        return modelService.createModel(model);
    }

    @PutMapping("/{id}")
    public UsagePatternModel updateModel(
            @PathVariable Long id,
            @RequestBody UsagePatternModel model) {
        return modelService.updateModel(id, model);
    }

    @GetMapping("/bin/{binId}")
    public UsagePatternModel getModelForBin(@PathVariable Long binId) {
        return modelService.getModelForBin(binId);
    }

    @GetMapping
    public List<UsagePatternModel> getAllModels() {
        return modelService.getAllModels();
    }
}
