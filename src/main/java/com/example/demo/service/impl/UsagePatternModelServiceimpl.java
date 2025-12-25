package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class UsagePatternModelServiceImpl implements UsagePatternModelService {
    
    private final UsagePatternModelRepository modelRepository;
    private final BinRepository binRepository;
    
    public UsagePatternModelServiceImpl(UsagePatternModelRepository modelRepository, BinRepository binRepository) {
        this.modelRepository = modelRepository;
        this.binRepository = binRepository;
    }
    
    @Override
    public UsagePatternModel createModel(UsagePatternModel model) {
        validateModel(model);
        
        Bin bin = binRepository.findById(model.getBin().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id: " + model.getBin().getId()));
        
        if (!bin.getActive()) {
            throw new BadRequestException("Cannot create model for inactive bin");
        }
        
        model.setBin(bin);
        model.setLastUpdated(LocalDateTime.now());
        
        return modelRepository.save(model);
    }
    
    @Override
    @Transactional(readOnly = true)
    public UsagePatternModel getModelById(Long id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usage pattern model not found with id: " + id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<UsagePatternModel> getModelForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        
        return modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin);
    }
    
    @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel modelUpdates) {
        UsagePatternModel existingModel = getModelById(id);
        
        if (modelUpdates.getAvgDailyIncreaseWeekday() != null) {
            if (modelUpdates.getAvgDailyIncreaseWeekday() < 0) {
                throw new BadRequestException("Weekday increase cannot be negative");
            }
            existingModel.setAvgDailyIncreaseWeekday(modelUpdates.getAvgDailyIncreaseWeekday());
        }
        
        if (modelUpdates.getAvgDailyIncreaseWeekend() != null) {
            if (modelUpdates.getAvgDailyIncreaseWeekend() < 0) {
                throw new BadRequestException("Weekend increase cannot be negative");
            }
            existingModel.setAvgDailyIncreaseWeekend(modelUpdates.getAvgDailyIncreaseWeekend());
        }
        
        existingModel.setLastUpdated(LocalDateTime.now());
        
        return modelRepository.save(existingModel);
    }
    
    @Override
    public void deleteModel(Long id) {
        UsagePatternModel model = getModelById(id);
        modelRepository.delete(model);
    }
    
    private void validateModel(UsagePatternModel model) {
        if (model.getAvgDailyIncreaseWeekday() == null || model.getAvgDailyIncreaseWeekday() < 0) {
            throw new BadRequestException("Weekday increase must be non-negative");
        }
        
        if (model.getAvgDailyIncreaseWeekend() == null || model.getAvgDailyIncreaseWeekend() < 0) {
            throw new BadRequestException("Weekend increase must be non-negative");
        }
        
        if (model.getBin() == null || model.getBin().getId() == null) {
            throw new BadRequestException("Bin is required for usage pattern model");
        }
    }
}