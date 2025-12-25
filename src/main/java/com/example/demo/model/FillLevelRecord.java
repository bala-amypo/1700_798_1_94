package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "fill_level_records")
public class FillLevelRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Fill percentage is required")
    @Min(value = 0, message = "Fill percentage cannot be less than 0")
    @Max(value = 100, message = "Fill percentage cannot exceed 100")
    private Double fillPercentage;
    
    @NotNull(message = "Record date/time is required")
    private LocalDateTime recordedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bin_id", nullable = false)
    @NotNull(message = "Bin is required")
    private Bin bin;
    
    // Constructors
    public FillLevelRecord() {}
    
    public FillLevelRecord(Long id, Double fillPercentage, LocalDateTime recordedAt) {
        this.id = id;
        this.fillPercentage = fillPercentage;
        this.recordedAt = recordedAt;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Double getFillPercentage() { return fillPercentage; }
    public void setFillPercentage(Double fillPercentage) { this.fillPercentage = fillPercentage; }
    
    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }
    
    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }
}