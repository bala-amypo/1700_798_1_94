package com.example.demo.controller;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usage-patterns")
public class UsagePatternController {

    private final UsagePatternService service;

    public UsagePatternController(UsagePatternService service) {
        this.service = service;
    }

    @GetMapping
    public List<UsagePatternModel> getAllUsagePatterns() {
        return service.getAllPatterns();
    }
}
