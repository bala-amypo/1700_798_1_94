package com.example.demo.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
@Entity
public class FillLevelRecord {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "bin_id", nullable = false)
    private Bin bin;

    @Min(0)
    @Max(100)
    @Column(nullable = false)
    private Double fillPercentage;

    @PastOrPresent
    @Column(nullable = false)
    private Instant recordedAt;
   private Boolean isWeekend;
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
   public Double getFillPercentage() {
    return fillPercentage;
   }
   public void setFillPercentage(Double fillPercentage) {
    this.fillPercentage = fillPercentage;
   }
   public Instant getRecordedAt() {
    return recordedAt;
   }
   public void setRecordedAt(Instant recordedAt) {
    this.recordedAt = recordedAt;
   }
   public Boolean getIsWeekend() {
    return isWeekend;
   }
   public void setIsWeekend(Boolean isWeekend) {
    this.isWeekend = isWeekend;
   }
   public FillLevelRecord() {
   }
   public FillLevelRecord(Long id, Bin bin, @Min(0) @Max(100) Double fillPercentage, @PastOrPresent Instant recordedAt,
        Boolean isWeekend) {
    this.id = id;
    this.bin = bin;
    this.fillPercentage = fillPercentage;
    this.recordedAt = recordedAt;
    this.isWeekend = isWeekend;
   }

}
