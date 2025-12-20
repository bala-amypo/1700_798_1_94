package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name="Zone",
    uniqueConstraints = @UniqueConstraint(columnNames="zoneName")
)
public class Zone {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique= true)
    private String zonename;
    private String description;
    @Column(nullable = false)
    private Boolean active=true;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getZonename() {
        return zonename;
    }
    public void setZonename(String zonename) {
        this.zonename = zonename;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public Zone() {
    }
    public Zone(Long id, String zonename, String description, Boolean active) {
        this.id = id;
        this.zonename = zonename;
        this.description = description;
        this.active = active;
    }
    

}
