package com.example.demo.service.impl;

import com.example.demo.service.UsagePatternModelService;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.exception.*;
import java.sql.Timestamp;
import java.util.List;

public class UsagePatternModelServiceImpl implements UsagePatternModelService {

    private final UsagePatternModelRepository modelRepository;
    private final BinRepository binRepository;

    public UsagePatternModelServiceImpl(UsagePatternModelRepository modelRepository,
                                        BinRepository binRepository) {
        this.modelRepository = modelRepository;
        this.binRepository = binRepository;
    }

    @Override
    public UsagePatternModel createModel(UsagePatternModel model) {

        if (model.getAvgDailyIncreaseWeekday() < 0 ||
            model.getAvgDailyIncreaseWeekend() < 0) {
            throw new BadRequestException("daily increase must be non-negative");
        }

        Bin bin = binRepository.findById(model.getBin().getId())
                .orElseThrow(() -> new ResourceNotFoundException("bin not found"));

        model.setBin(bin);
        model.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        return modelRepository.save(model);
    }

    @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel model) {
        UsagePatternModel existing = modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("model not found"));

        existing.setAvgDailyIncreaseWeekday(model.getAvgDailyIncreaseWeekday());
        existing.setAvgDailyIncreaseWeekend(model.getAvgDailyIncreaseWeekend());
        existing.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        return modelRepository.save(existing);
    }

    @Override
    public UsagePatternModel getModelForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("bin not found"));

        return modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("model not found"));
    }

    @Override
    public List<UsagePatternModel> getAllModels() {
        return modelRepository.findAll();
    }
}
