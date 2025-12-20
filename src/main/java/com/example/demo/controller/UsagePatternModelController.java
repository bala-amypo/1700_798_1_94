package com.example.demo.controller;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
public class UsagePatternModelController {

    private final UsagePatternModelService service;

    public UsagePatternModelController(UsagePatternModelService service) {
        this.service = service;
    }

    @PostMapping
    public UsagePatternModel create(@RequestBody UsagePatternModel model) {
        return service.createModel(model);
    }

    @PutMapping("/{id}")
    public UsagePatternModel update(@PathVariable Long id,
                                    @RequestBody UsagePatternModel model) {
        return service.updateModel(id, model);
    }

    @GetMapping("/bin/{binId}")
    public UsagePatternModel getForBin(@PathVariable Long binId) {
        return service.getModelForBin(binId);
    }

    @GetMapping
    public List<UsagePatternModel> getAll() {
        return service.getAllModels();
    }
}
