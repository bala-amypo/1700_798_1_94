package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.BinService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BinServiceImpl implements BinService {
    
    private final BinRepository binRepository;
    private final ZoneRepository zoneRepository;
    
    public BinServiceImpl(BinRepository binRepository, ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.zoneRepository = zoneRepository;
    }
    
    @Override
    public Bin createBin(Bin bin) {
        validateBin(bin);
        
        // Check if zone exists and is active
        Zone zone = zoneRepository.findById(bin.getZone().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id: " + bin.getZone().getId()));
        
        if (!zone.getActive()) {
            throw new BadRequestException("Cannot assign bin to inactive zone");
        }
        
        bin.setZone(zone);
        
        if (bin.getActive() == null) {
            bin.setActive(true);
        }
        
        return binRepository.save(bin);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Bin getBinById(Long id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id: " + id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }
    
    @Override
    public Bin updateBin(Long id, Bin binUpdates) {
        Bin existingBin = getBinById(id);
        
        // Update only non-null fields
        if (binUpdates.getLocationDescription() != null) {
            existingBin.setLocationDescription(binUpdates.getLocationDescription());
        }
        if (binUpdates.getLatitude() != null) {
            existingBin.setLatitude(binUpdates.getLatitude());
        }
        if (binUpdates.getLongitude() != null) {
            existingBin.setLongitude(binUpdates.getLongitude());
        }
        if (binUpdates.getCapacityLiters() != null) {
            if (binUpdates.getCapacityLiters() <= 0) {
                throw new BadRequestException("Capacity must be greater than 0");
            }
            existingBin.setCapacityLiters(binUpdates.getCapacityLiters());
        }
        if (binUpdates.getZone() != null && binUpdates.getZone().getId() != null) {
            Zone zone = zoneRepository.findById(binUpdates.getZone().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
            if (!zone.getActive()) {
                throw new BadRequestException("Cannot assign bin to inactive zone");
            }
            existingBin.setZone(zone);
        }
        
        return binRepository.save(existingBin);
    }
    
    @Override
    public void deactivateBin(Long id) {
        Bin bin = getBinById(id);
        bin.setActive(false);
        binRepository.save(bin);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Bin> getBinsByZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        return binRepository.findByZone(zone);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Bin> getActiveBins() {
        return binRepository.findAllByActiveTrue();
    }
    
    private void validateBin(Bin bin) {
        if (bin.getCapacityLiters() == null || bin.getCapacityLiters() <= 0) {
            throw new BadRequestException("Bin capacity must be greater than 0");
        }
        if (bin.getIdentifier() == null || bin.getIdentifier().trim().isEmpty()) {
            throw new BadRequestException("Bin identifier is required");
        }
        if (bin.getZone() == null || bin.getZone().getId() == null) {
            throw new BadRequestException("Zone is required for bin");
        }
    }
}