package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Bin;
import com.example.demo.repository.BinRepository;
import com.example.demo.service.BinService;

@Service
public class BinServiceImpl implements BinService {

    @Autowired
    private BinRepository repo;

    @Override
    public Bin createBin(Bin bin) {
        return repo.save(bin);
    }

    @Override
    public List<Bin> getAllBins() {
        return repo.findAll();
    }

    @Override
    public Bin getBinById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public Bin updateBin(Long id, Bin bin) {
        Bin existing = getBinById(id);
        existing.setIdentifier(bin.getIdentifier());
        existing.setCapacityLiters(bin.getCapacityLiters());
        return repo.save(existing);
    }

    @Override
    public void deactivateBin(Long id) {
        Bin bin = getBinById(id);
        bin.setActive(false);
        repo.save(bin);
    }
}
