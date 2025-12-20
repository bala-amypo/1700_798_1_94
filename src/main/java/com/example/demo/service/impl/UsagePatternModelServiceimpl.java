package com.example.demo.service.impl;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsagePatternModelServiceImpl implements UsagePatternModelService {

    private final UsagePatternModelRepository usagePatternModelRepository;

    public UsagePatternModelServiceImpl(UsagePatternModelRepository usagePatternModelRepository) {
        this.usagePatternModelRepository = usagePatternModelRepository;
    }

    @Override
    public UsagePatternModel createUsagePatternModel(UsagePatternModel model) {
        return usagePatternModelRepository.save(model);
    }

    @Override
    public List<UsagePatternModel> getUsagePatternsForBin(Long binId) {
        // Adjust method name in repository according to your actual repo
        return usagePatternModelRepository.findByBinIdOrderByCreatedAtDesc(binId);
    }
}
