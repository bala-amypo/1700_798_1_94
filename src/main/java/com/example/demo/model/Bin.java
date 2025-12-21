package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Bin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;
    private String locationDescription;
    private Double latitude;
    private Double longitude;
    private Double capacityLiters;
    private boolean active;

    @ManyToOne
    private Zone zone;


    public Long getId() {
     return id; 
     }
    public String getIdentifier() { 
        return identifier; 
        }
    public String getLocationDescription() {
     return locationDescription; 
     }
    public Double getLatitude() {
         return latitude; 
         }
    public Double getLongitude() {
     return longitude; 
     }
    public Double getCapacityLiters() { 
        return capacityLiters; 
        }
    public boolean isActive() {
         return active; 
         }
    public Zone getZone() {
         return zone;
          }

    public void setIdentifier(String identifier) {
     this.identifier = identifier;
      }
    public void setLocationDescription(String locationDescription) { 
        this.locationDescription = locationDescription; 
        }
    public void setLatitude(Double latitude) { 
        this.latitude = latitude;
         }
    public void setLongitude(Double longitude) { 
        this.longitude = longitude;
         }
    public void setCapacityLiters(Double capacityLiters) {
         this.capacityLiters = capacityLiters;
          }
    public void setActive(boolean active) {
         this.active = active; 
         }
    public void setZone(Zone zone) {
         this.zone = zone; }
}
