package com.example.demo.service.impl;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;

@Service
public class UsagePatternModelServiceImpl implements UsagePatternModelService {

    private final UsagePatternModelRepository repository;

    @Autowired
    public UsagePatternModelServiceImpl(UsagePatternModelRepository repository) {
        this.repository = repository;
    }

    @Override
    public UsagePatternModel save(UsagePatternModel model) {
        model.setCreatedAt(Instant.now());
        return repository.save(model);
    }

    @Override
    public UsagePatternModel update(Long id, UsagePatternModel model) {
        UsagePatternModel existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usage pattern not found"));

        existing.setPatternName(model.getPatternName());
        existing.setDescription(model.getDescription());
        existing.setUpdatedAt(Instant.now());

        return repository.save(existing);
    }

    @Override
    public List<UsagePatternModel> getAll() {
        return repository.findAll();
    }
}
