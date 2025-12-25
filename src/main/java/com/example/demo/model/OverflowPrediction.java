package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "overflow_predictions")
public class OverflowPrediction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Days until full is required")
    private Integer daysUntilFull;
    
    @NotNull(message = "Predicted full date is required")
    private LocalDate predictedFullDate;
    
    @Column(nullable = false)
    private LocalDateTime predictedAt = LocalDateTime.now();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bin_id", nullable = false)
    @NotNull(message = "Bin is required")
    private Bin bin;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    @NotNull(message = "Model is required")
    private UsagePatternModel modelUsed;
    
    // Constructors
    public OverflowPrediction() {}
    
    public OverflowPrediction(Integer daysUntilFull, LocalDate predictedFullDate) {
        this.daysUntilFull = daysUntilFull;
        this.predictedFullDate = predictedFullDate;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Integer getDaysUntilFull() { return daysUntilFull; }
    public void setDaysUntilFull(Integer daysUntilFull) { this.daysUntilFull = daysUntilFull; }
    
    public LocalDate getPredictedFullDate() { return predictedFullDate; }
    public void setPredictedFullDate(LocalDate predictedFullDate) { this.predictedFullDate = predictedFullDate; }
    
    public LocalDateTime getPredictedAt() { return predictedAt; }
    public void setPredictedAt(LocalDateTime predictedAt) { this.predictedAt = predictedAt; }
    
    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }
    
    public UsagePatternModel getModelUsed() { return modelUsed; }
    public void setModelUsed(UsagePatternModel modelUsed) { this.modelUsed = modelUsed; }
}