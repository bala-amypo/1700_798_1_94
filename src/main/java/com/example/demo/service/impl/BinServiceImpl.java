package com.example.demo.service.impl;

import com.example.demo.service.BinService;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.exception.*;
import java.sql.Timestamp;
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
        bin.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        bin.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return binRepository.save(bin);
    }

    @Override
    public Bin updateBin(Long id, Bin bin) {
        Bin existing = getBinById(id);
        existing.setLocationDescription(bin.getLocationDescription());
        existing.setLatitude(bin.getLatitude());
        existing.setLongitude(bin.getLongitude());
        existing.setCapacityLiters(bin.getCapacityLiters());
        existing.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
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
