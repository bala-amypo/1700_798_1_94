package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;

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

        model = new UsagePatternModel(
                bin,
                model.getAvgDailyIncreaseWeekday(),
                model.getAvgDailyIncreaseWeekend(),
                model.getLastUpdated()
        );

        return modelRepository.save(model);
    }

    @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel model) {
        UsagePatternModel existing = modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("model not found"));

        existing = new UsagePatternModel(
                existing.getBin(),
                model.getAvgDailyIncreaseWeekday(),
                model.getAvgDailyIncreaseWeekend(),
                model.getLastUpdated()
        );

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
