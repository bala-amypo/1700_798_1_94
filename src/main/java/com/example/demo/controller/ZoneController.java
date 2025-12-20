package com.example.demo.controller;

import com.example.demo.model.Zone;
import com.example.demo.service.ZoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {
    private final ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @PostMapping
    public ResponseEntity<Zone> createZone(@RequestBody Zone zone) {
        return ResponseEntity.ok(zoneService.createZone(zone));
    }

    @GetMapping
    public ResponseEntity<List<Zone>> getAll() {
        return ResponseEntity.ok(zoneService.getAllZones());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        zoneService.deactivateZone(id);
        return ResponseEntity.ok().build();
    }
}