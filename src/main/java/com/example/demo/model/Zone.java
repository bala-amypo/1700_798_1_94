package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zone {
    private Long id;
    private String zoneName;
    private String description;
    private Boolean active;
}
