package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Zone;

public interface ZoneService {
    Zone createZone(Zone zone);
    List<Zone> getAllZones();
    Zone getZoneById(Long id);
}
