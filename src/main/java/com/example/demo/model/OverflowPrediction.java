package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "overflow_predictions")
public class OverflowPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bin_id")
    private Bin bin;

    @Column(name = "predicted_fill_level")
    private Double predictedFillLevel;

    @Column(name = "predicted_time")
    private Timestamp predictedTime;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }

    public Double getPredictedFillLevel() { return predictedFillLevel; }
    public void setPredictedFillLevel(Double predictedFillLevel) { this.predictedFillLevel = predictedFillLevel; }

    public Timestamp getPredictedTime() { return predictedTime; }
    public void setPredictedTime(Timestamp predictedTime) { this.predictedTime = predictedTime; }
}
