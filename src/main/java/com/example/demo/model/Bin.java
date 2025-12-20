package com.example.demo.model;
import java.time.Instant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(
    name="bin",
    uniqueConstraints={
        @UniqueConstraint(columnNames="identifier")
    }
)
public class Bin {
   @Id 
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long id;
    @Column(nullable = false, unique = true)
   private String identifer;
   private String locationDescription;
   private Double latitude;
   private Double longitude;
   @ManyToOne
   @JoinColumn(name="Zone_id")
   private Zone zone;
   @Column(nullable = false)
   private Double capcityLiters;
   @Column(nullable = false)
   private Boolean active = true;
   @Column(nullable = false, updatable = false)
    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifer() {
        return identifer;
    }

    public void setIdentifer(String identifer) {
        this.identifer = identifer;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Double getCapcityLiters() {
        return capcityLiters;
    }

    public void setCapcityLiters(Double capcityLiters) {
        this.capcityLiters = capcityLiters;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public Bin() {
    }

    public Bin(Long id, String identifer, String locationDescription, Double latitude, Double longitude, Zone zone,
            Double capcityLiters, Boolean active, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.identifer = identifer;
        this.locationDescription = locationDescription;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zone = zone;
        this.capcityLiters = capcityLiters;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }




}
