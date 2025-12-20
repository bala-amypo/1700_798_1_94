package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String zoneName;
    private String description;
    private boolean active;

    // ===== Getters =====
    public Long getId() { return id; }
    public String getZoneName() { return zoneName; }
    public String getDescription() { return description; }
    public boolean isActive() { return active; }

    // ===== Setters =====
    public void setZoneName(String zoneName) { this.zoneName = zoneName; }
    public void setDescription(String description) { this.description = description; }
    public void setActive(boolean active) { this.active = active; }
}
