package com.example.demo.repository;

import com.example.demo.model.OverflowPrediction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OverflowPredictionRepository extends JpaRepository<OverflowPrediction, Long> {

    // Add this method for the latest prediction
    List<OverflowPrediction> findTop1ByBinIdOrderByCreatedAtDesc(Long binId);

    // You can also add these if needed
    List<OverflowPrediction> findByBinId(Long binId);

    List<OverflowPrediction> findTopByZoneIdOrderByCreatedAtDesc(Long zoneId);
}
