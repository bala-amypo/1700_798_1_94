package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "usage_pattern_models")
public class UsagePatternModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Weekday increase is required")
    @Min(value = 0, message = "Weekday increase cannot be negative")
    private Double avgDailyIncreaseWeekday;
    
    @NotNull(message = "Weekend increase is required")
    @Min(value = 0, message = "Weekend increase cannot be negative")
    private Double avgDailyIncreaseWeekend;
    
    @Column(nullable = false)
    private LocalDateTime lastUpdated = LocalDateTime.now();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bin_id", nullable = false)
    @NotNull(message = "Bin is required")
    private Bin bin;
    
    // Constructors
    public UsagePatternModel() {}
    
    public UsagePatternModel(Double avgDailyIncreaseWeekday, Double avgDailyIncreaseWeekend) {
        this.avgDailyIncreaseWeekday = avgDailyIncreaseWeekday;
        this.avgDailyIncreaseWeekend = avgDailyIncreaseWeekend;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Double getAvgDailyIncreaseWeekday() { return avgDailyIncreaseWeekday; }
    public void setAvgDailyIncreaseWeekday(Double avgDailyIncreaseWeekday) { 
        this.avgDailyIncreaseWeekday = avgDailyIncreaseWeekday; 
    }
    
    public Double getAvgDailyIncreaseWeekend() { return avgDailyIncreaseWeekend; }
    public void setAvgDailyIncreaseWeekend(Double avgDailyIncreaseWeekend) { 
        this.avgDailyIncreaseWeekend = avgDailyIncreaseWeekend; 
    }
    
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
    
    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }
}