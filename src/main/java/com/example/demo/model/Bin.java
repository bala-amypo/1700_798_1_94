package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bins")
public class Bin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Identifier is required")
    @Column(unique = true, nullable = false)
    private String identifier;
    
    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1 liter")
    private Double capacityLiters;
    
    private Double latitude;
    private Double longitude;
    
    private String locationDescription;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id", nullable = false)
    @NotNull(message = "Zone is required")
    private Zone zone;
    
    // Constructors
    public Bin() {}
    
    public Bin(Long id, String identifier, Double capacityLiters) {
        this.id = id;
        this.identifier = identifier;
        this.capacityLiters = capacityLiters;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getIdentifier() { return identifier; }
    public void setIdentifier(String identifier) { this.identifier = identifier; }
    
    public Double getCapacityLiters() { return capacityLiters; }
    public void setCapacityLiters(Double capacityLiters) { this.capacityLiters = capacityLiters; }
    
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    
    public String getLocationDescription() { return locationDescription; }
    public void setLocationDescription(String locationDescription) { this.locationDescription = locationDescription; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public Zone getZone() { return zone; }
    public void setZone(Zone zone) { this.zone = zone; }
}