package com.example.demo.service;

import com.example.demo.model.Zone;
import java.util.List;

public interface ZoneService {
    Zone saveZone(Zone zone);
    List<Zone> getAllZones();
}
