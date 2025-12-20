package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "fill_level_records")
public class FillLevelRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bin_id")
    private Bin bin;

    @Column(name = "fill_level_percentage")
    private Double fillLevelPercentage;

    @Column(name = "recorded_at")
    private Timestamp recordedAt;

    @Column(name = "is_weekend")
    private Boolean isWeekend;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }

    public Double getFillLevelPercentage() { return fillLevelPercentage; }
    public void setFillLevelPercentage(Double fillLevelPercentage) { this.fillLevelPercentage = fillLevelPercentage; }

    public Timestamp getRecordedAt() { return recordedAt; }
    public void setRecordedAt(Timestamp recordedAt) { this.recordedAt = recordedAt; }

    public Boolean getIsWeekend() { return isWeekend; }
    public void setIsWeekend(Boolean isWeekend) { this.isWeekend = isWeekend; }
}
