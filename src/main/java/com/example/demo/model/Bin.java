package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Table(name = "bins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String identifier;
    private String locationDescription;
    private Double latitude;
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;

    private Double capacityLiters;
    private Boolean active = true;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}