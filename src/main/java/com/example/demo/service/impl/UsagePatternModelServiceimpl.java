package com.example.demo.service.impl;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsagePatternModelServiceimpl implements UsagePatternModelService {

    private final UsagePatternModelRepository usagePatternModelRepository;

    public UsagePatternModelServiceimpl(UsagePatternModelRepository usagePatternModelRepository) {
        this.usagePatternModelRepository = usagePatternModelRepository;
    }

    @Override
    public UsagePatternModel createModel(UsagePatternModel model) {
        return usagePatternModelRepository.save(model);
    }

    @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel model) {
        model.setId(id);
        return usagePatternModelRepository.save(model);
    }

    @Override
    public List<UsagePatternModel> getModelForBin(Long binId) {
        return usagePatternModelRepository.findByBinIdOrderByCreatedAtDesc(binId);
    }

    @Override
    public List<UsagePatternModel> getAllModels() {
        return usagePatternModelRepository.findAll();
    }
}
