package com.example.demo.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "usage_pattern_model")
public class UsagePatternModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String patternName;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    /* =======================
       JPA Lifecycle Callbacks
       ======================= */

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

    /* =======================
       Constructors
       ======================= */

    public UsagePatternModel() {
    }

    public UsagePatternModel(Long id, String patternName, String description,
                             Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.patternName = patternName;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /* =======================
       Getters and Setters
       ======================= */

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
