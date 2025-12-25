package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zones")
public class Zone {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Zone name is required")
    @Column(unique = true, nullable = false)
    private String zoneName;
    
    private String description;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bin> bins = new ArrayList<>();
    
    // Constructors
    public Zone() {}
    
    public Zone(Long id, String zoneName) {
        this.id = id;
        this.zoneName = zoneName;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getZoneName() { return zoneName; }
    public void setZoneName(String zoneName) { this.zoneName = zoneName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public List<Bin> getBins() { return bins; }
    public void setBins(List<Bin> bins) { this.bins = bins; }
}