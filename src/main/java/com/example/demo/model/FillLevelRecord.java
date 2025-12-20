package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class FillLevelRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Bin bin;

    private Double fillLevel;
    private LocalDateTime createdAt;

    // ===== Getters =====
    public Long getId() { return id; }
    public Bin getBin() { return bin; }
    public Double getFillLevel() { return fillLevel; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // ===== Setters =====
    public void setBin(Bin bin) { this.bin = bin; }
    public void setFillLevel(Double fillLevel) { this.fillLevel = fillLevel; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
