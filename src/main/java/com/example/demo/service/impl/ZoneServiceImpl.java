package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;
@Service
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    private ZoneRepository repo;

    @Override
    public Zone createZone(Zone zone) {
        return repo.save(zone);
    }

    @Override
    public List<Zone> getAllZones() {
        return repo.findAll();
    }

    @Override
    public void deactivateZone(Long id) {
        Zone zone = repo.findById(id).orElseThrow();
        zone.setActive(false);
        repo.save(zone);
    }
}
