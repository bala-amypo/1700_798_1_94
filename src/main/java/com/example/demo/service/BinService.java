package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Bin;

public interface BinService {
    Bin createBin(Bin bin);
    List<Bin> getAllBins();
    Bin getBinById(Long id);
    Bin updateBin(Long id, Bin bin);
    void deactivateBin(Long id);
}
