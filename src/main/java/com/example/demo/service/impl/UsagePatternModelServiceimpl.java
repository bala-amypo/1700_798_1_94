package com.example.demo.service.impl;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.UsagePatternRepository;
import com.example.demo.service.UsagePatternService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsagePatternServiceImpl implements UsagePatternService {

    private final UsagePatternRepository repository;

    public UsagePatternServiceImpl(UsagePatternRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UsagePatternModel> getAllPatterns() {
        return repository.findAll();
    }
}
