package com.example.demo.controller;

import com.example.demo.model.Bin;
import com.example.demo.service.BinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bins")
@Tag(name = "Bin Management")
public class BinController {
    private final BinService binService;
    public BinController(BinService binService) {
        this.binService = binService;
    }

    @PostMapping
    @Operation(summary = "Add a new bin")
    public ResponseEntity<Bin> add(@RequestBody Bin bin) {
        return ResponseEntity.ok(binService.createBin(bin));
    }

    @GetMapping
    public ResponseEntity<List<Bin>> getAll() {
        return ResponseEntity.ok(binService.getAllBins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bin> getById(@PathVariable Long id) {
        return ResponseEntity.ok(binService.getBinById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bin> update(@PathVariable Long id, @RequestBody Bin bin) {
        return ResponseEntity.ok(binService.updateBin(id, bin));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        binService.deactivateBin(id);
        return ResponseEntity.ok().build();
    }
}