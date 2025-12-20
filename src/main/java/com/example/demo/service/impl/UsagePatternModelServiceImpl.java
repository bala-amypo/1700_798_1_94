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

    @Autowired
    private UsagePatternModelRepository repo;

    @Override
    public UsagePatternModel createModel(UsagePatternModel model) {
        model.setCreatedAt(Instant.now());
        return repo.save(model);
    }

    @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel model) {
        UsagePatternModel existing = repo.findById(id).orElseThrow();
        existing.setPatternName(model.getPatternName());
        existing.setDescription(model.getDescription());
        existing.setUpdatedAt(Instant.now());
        return repo.save(existing);
    }

    @Override
    public UsagePatternModel getModelForBin(Long binId) {
        return repo.findByBinId(binId);
    }

    @Override
    public List<UsagePatternModel> getAllModels() {
        return repo.findAll();
    }
}
