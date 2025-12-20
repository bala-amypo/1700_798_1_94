package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.BinService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class BinServiceImpl implements BinService {

    private final BinRepository binRepository;
    private final ZoneRepository zoneRepository;

    public BinServiceImpl(BinRepository binRepository, ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Bin createBin(Bin bin) {
        if (bin.getCapacityLiters() == null || bin.getCapacityLiters() <= 0) {
            throw new BadRequestException("capacity must be greater than 0");
        }

        if (binRepository.findByIdentifier(bin.getIdentifier()).isPresent()) {
            throw new BadRequestException("identifier already exists");
        }

        Zone zone = zoneRepository.findById(bin.getZone().getId())
                .orElseThrow(() -> new ResourceNotFoundException("zone not found"));

        bin.setZone(zone);
        bin.setActive(true);

        Timestamp now = Timestamp.from(Instant.now());
        bin.setActive(true);

        return binRepository.save(bin);
    }

    @Override
    public Bin updateBin(Long id, Bin bin) {
        Bin existing = getBinById(id);

        existing.setIdentifier(bin.getIdentifier());
        existing.setLocationDescription(bin.getLocationDescription());
        existing.setCapacityLiters(bin.getCapacityLiters());
        existing.setZone(bin.getZone());

        return binRepository.save(existing);
    }

    @Override
    public Bin getBinById(Long id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("bin not found"));
    }

    @Override
    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    @Override
    public void deactivateBin(Long id) {
        Bin bin = getBinById(id);
        bin.setActive(false);
        binRepository.save(bin);
    }
}
