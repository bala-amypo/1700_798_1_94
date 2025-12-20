package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bin {
    private Long id;
    private String identifier;
    private String locationDescription;
    private Double latitude;
    private Double longitude;
    private Zone zone;
    private Double capacityLiters;
    private Boolean active;
}
