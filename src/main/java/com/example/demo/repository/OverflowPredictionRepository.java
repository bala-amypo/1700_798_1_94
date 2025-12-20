package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;

import java.util.List;

public interface OverflowPredictionRepository extends JpaRepository<OverflowPrediction, Long> {

    List<OverflowPrediction> findByBin(Bin bin);
}
