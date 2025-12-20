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

    private final BinRepository binRepository;

    @Autowired
    public BinServiceImpl(BinRepository binRepository) {
        this.binRepository = binRepository;
    }

    @Override
    public Bin createBin(Bin bin) {
        Optional<Bin> existing = binRepository.findByIdentifier(bin.getIdentifier());
        if (existing.isPresent()) {
            throw new RuntimeException("Bin with identifier already exists");
        }
        return binRepository.save(bin);
    }

    @Override
    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    @Override
    public Bin getBinById(Long id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bin not found"));
    }

    @Override
    public Bin updateBin(Long id, Bin bin) {
        Bin existing = getBinById(id);

        existing.setIdentifier(bin.getIdentifier());
        existing.setLocationDescription(bin.getLocationDescription());
        existing.setLatitude(bin.getLatitude());
        existing.setLongitude(bin.getLongitude());
        existing.setCapacityLiters(bin.getCapacityLiters());
        existing.setZone(bin.getZone());
        existing.setActive(bin.getActive());

        return binRepository.save(existing);
    }

    @Override
    public void deleteBin(Long id) {
        binRepository.deleteById(id);
    }
}
