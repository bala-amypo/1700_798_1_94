package com.example.demo.repository;

import com.example.demo.model.OverflowPrediction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OverflowPredictionRepository extends JpaRepository<OverflowPrediction, Long> {
    List<OverflowPrediction> findTop1ByBinIdOrderByCreatedAtDesc(Long binId);
    List<OverflowPrediction> findByBinId(Long binId);
    List<OverflowPrediction> findTopByZoneIdOrderByCreatedAtDesc(Long zoneId);
}
