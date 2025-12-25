package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.model.Bin;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.repository.BinRepository;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.stereotype.Service;

@Service
public class UsagePatternModelServiceImpl implements UsagePatternModelService {
    private final UsagePatternModelRepository modelRepository;
    private final BinRepository binRepository;

    public UsagePatternModelServiceImpl(UsagePatternModelRepository modelRepository, BinRepository binRepository) {
        this.modelRepository = modelRepository;
        this.binRepository = binRepository;
    }

    public UsagePatternModel createModel(UsagePatternModel model) {
        if (model.getAvgDailyIncreaseWeekday() < 0) {
            throw new BadRequestException("Daily increase cannot be negative");
        }
        
        Bin bin = binRepository.findById(model.getBin().getId())
                .orElseThrow(() -> new BadRequestException("Bin not found"));
        
        return modelRepository.save(model);
    }

    public UsagePatternModel getModelForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        
        return modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found for bin"));
    }

    public UsagePatternModel updateModel(Long id, UsagePatternModel update) {
        UsagePatternModel model = modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found"));
        
        if (update.getAvgDailyIncreaseWeekend() != null) {
            model.setAvgDailyIncreaseWeekend(update.getAvgDailyIncreaseWeekend());
        }
        
        return modelRepository.save(model);
    }
}