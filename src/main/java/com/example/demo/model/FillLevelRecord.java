package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class FillLevelRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bin_id", nullable = false)
    private Bin bin;

    private Double fillPercentage;

    private Timestamp recordedAt;

    // Getter and setter for 'bin'
    public Bin getBin() {
        return bin;
    }

    public void setBin(Bin bin) {
        this.bin = bin;
    }

    // Other getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getFillPercentage() {
        return fillPercentage;
    }

    public void setFillPercentage(Double fillPercentage) {
        this.fillPercentage = fillPercentage;
    }

    public Timestamp getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(Timestamp recordedAt) {
        this.recordedAt = recordedAt;
    }
}
