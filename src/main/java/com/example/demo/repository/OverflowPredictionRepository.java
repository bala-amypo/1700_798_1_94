package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.OverflowPrediction;

public interface OverflowPredictionRepository 
        extends JpaRepository<OverflowPrediction, Long> {

    List<OverflowPrediction> findByBinId(Long binId);
}
