package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "zones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String zoneName;
    private String description;
    private Boolean active = true;

    public Zone(String zoneName, String description, Boolean active) {
        this.zoneName = zoneName;
        this.description = description;
        this.active = active;
    }
}