package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Bin;
import com.example.demo.service.BinService;

@RestController
@RequestMapping("/api/bins")
public class BinController {

    private final BinService binService;

    @Autowired
    public BinController(BinService binService) {
        this.binService = binService;
    }

    @PostMapping
    public Bin createBin(@RequestBody Bin bin) {
        return binService.createBin(bin);
    }

    @PutMapping("/{id}")
    public Bin updateBin(@PathVariable Long id, @RequestBody Bin bin) {
        return binService.updateBin(id, bin);
    }

    @GetMapping("/{id}")
    public Bin getBin(@PathVariable Long id) {
        return binService.getBinById(id);
    }

    @GetMapping
    public List<Bin> getAllBins() {
        return binService.getAllBins();
    }

    @PutMapping("/{id}/deactivate")
    public String deactivateBin(@PathVariable Long id) {
        binService.deactivateBin(id);
        return "Bin deactivated successfully";
    }
}
