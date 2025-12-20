package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class OverflowPrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Bin bin;

    @ManyToOne
    private Zone zone;

    private LocalDateTime createdAt;
    private Double predictedFillLevel;

    // ===== Getters =====
    public Long getId() { return id; }
    public Bin getBin() { return bin; }
    public Zone getZone() { return zone; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Double getPredictedFillLevel() { return predictedFillLevel; }

    // ===== Setters =====
    public void setBin(Bin bin) { this.bin = bin; }
    public void setZone(Zone zone) { this.zone = zone; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setPredictedFillLevel(Double predictedFillLevel) { this.predictedFillLevel = predictedFillLevel; }
}
