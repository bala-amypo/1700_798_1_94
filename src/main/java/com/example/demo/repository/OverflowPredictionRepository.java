package com.example.demo.repository;

import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OverflowPredictionRepository extends JpaRepository<OverflowPrediction, Long> {
    
    Optional<OverflowPrediction> findTop1ByBinOrderByPredictedAtDesc(Bin bin);
    
    List<OverflowPrediction> findByBinOrderByPredictedAtDesc(Bin bin);
    
    @Query("SELECT op FROM OverflowPrediction op " +
           "JOIN op.bin b " +
           "WHERE b.zone = :zone " +
           "ORDER BY op.predictedAt DESC")
    List<OverflowPrediction> findLatestPredictionsForZone(@Param("zone") Zone zone);
    
    @Query("SELECT DISTINCT op FROM OverflowPrediction op " +
           "JOIN op.bin b " +
           "WHERE b.zone = :zone " +
           "AND op.predictedAt = (SELECT MAX(op2.predictedAt) " +
           "                      FROM OverflowPrediction op2 " +
           "                      WHERE op2.bin = op.bin)")
    List<OverflowPrediction> findLatestPredictionsForEachBinInZone(@Param("zone") Zone zone);
}