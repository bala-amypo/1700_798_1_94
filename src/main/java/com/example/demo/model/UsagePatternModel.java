package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usage_pattern")
public class UsagePatternModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patternName;
    private Double averageUsage;

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }

    public Double getAverageUsage() {
        return averageUsage;
    }

    public void setAverageUsage(Double averageUsage) {
        this.averageUsage = averageUsage;
    }
}
