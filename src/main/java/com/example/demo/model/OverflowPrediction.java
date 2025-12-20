package com.example.demo.model;

import java.time.Instant;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;

@Entity

public class OverflowPrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "bin_id")
    private Bin bin;
    @FutureOrPresent
    private LocalDate predictedFullDate;

    @Min(0)
    private Integer daysUntilFull;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private UsagePatternModel modelUsed;

    @Column(nullable = false, updatable = false)
    private Instant generatedAt;

    @PrePersist
    protected void onGenerate() {
        generatedAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bin getBin() {
        return bin;
    }

    public void setBin(Bin bin) {
        this.bin = bin;
    }

    public LocalDate getPredictedFullDate() {
        return predictedFullDate;
    }

    public void setPredictedFullDate(LocalDate predictedFullDate) {
        this.predictedFullDate = predictedFullDate;
    }

    public Integer getDaysUntilFull() {
        return daysUntilFull;
    }

    public void setDaysUntilFull(Integer daysUntilFull) {
        this.daysUntilFull = daysUntilFull;
    }

    public UsagePatternModel getModelUsed() {
        return modelUsed;
    }

    public void setModelUsed(UsagePatternModel modelUsed) {
        this.modelUsed = modelUsed;
    }

    public Instant getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(Instant generatedAt) {
        this.generatedAt = generatedAt;
    }

    public OverflowPrediction() {
    }

    public OverflowPrediction(Long id, Bin bin, @FutureOrPresent LocalDate predictedFullDate,
            @Min(0) Integer daysUntilFull, UsagePatternModel modelUsed, Instant generatedAt) {
        this.id = id;
        this.bin = bin;
        this.predictedFullDate = predictedFullDate;
        this.daysUntilFull = daysUntilFull;
        this.modelUsed = modelUsed;
        this.generatedAt = generatedAt;
    }
}
