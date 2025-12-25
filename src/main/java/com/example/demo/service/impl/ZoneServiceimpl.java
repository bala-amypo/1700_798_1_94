package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ZoneServiceImpl implements ZoneService {
    
    private final ZoneRepository zoneRepository;
    
    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }
    
    @Override
    public Zone createZone(Zone zone) {
        if (zone.getZoneName() == null || zone.getZoneName().trim().isEmpty()) {
            throw new BadRequestException("Zone name is required");
        }
        
        // Check for duplicate zone name
        Optional<Zone> existingZone = zoneRepository.findByZoneName(zone.getZoneName());
        if (existingZone.isPresent()) {
            throw new BadRequestException("Zone with name '" + zone.getZoneName() + "' already exists");
        }
        
        if (zone.getActive() == null) {
            zone.setActive(true);
        }
        
        return zoneRepository.save(zone);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Zone getZoneById(Long id) {
        return zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id: " + id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }
    
    @Override
    public Zone updateZone(Long id, Zone zoneUpdates) {
        Zone existingZone = getZoneById(id);
        
        if (zoneUpdates.getZoneName() != null && !zoneUpdates.getZoneName().trim().isEmpty()) {
            // Check if new name already exists (excluding current zone)
            Optional<Zone> zoneWithSameName = zoneRepository.findByZoneName(zoneUpdates.getZoneName());
            if (zoneWithSameName.isPresent() && !zoneWithSameName.get().getId().equals(id)) {
                throw new BadRequestException("Zone name '" + zoneUpdates.getZoneName() + "' already exists");
            }
            existingZone.setZoneName(zoneUpdates.getZoneName());
        }
        
        if (zoneUpdates.getDescription() != null) {
            existingZone.setDescription(zoneUpdates.getDescription());
        }
        
        return zoneRepository.save(existingZone);
    }
    
    @Override
    public void deactivateZone(Long id) {
        Zone zone = getZoneById(id);
        zone.setActive(false);
        zoneRepository.save(zone);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Zone> getZoneByName(String zoneName) {
        return zoneRepository.findByZoneName(zoneName);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Zone> getActiveZones() {
        return (List<Zone>) zoneRepository.findAllByActiveTrue();
    }
}