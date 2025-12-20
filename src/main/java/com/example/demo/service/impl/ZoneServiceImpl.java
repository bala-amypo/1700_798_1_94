package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;

@Service
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository repository;

    @Autowired
    public ZoneServiceImpl(ZoneRepository repository) {
        this.repository = repository;
    }

    @Override
    public Zone createZone(Zone zone) {
        return repository.save(zone);
    }

    @Override
    public List<Zone> getAllZones() {
        return repository.findAll();
    }

    @Override
    public String getZoneName(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zone not found"))
                .getName(); // ✅ NOT getZoneName()
    }
}
