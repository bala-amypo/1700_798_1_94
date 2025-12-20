package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FillLevelRecord {
    private Long id;
    private Bin bin;
    private double fillLevelPercentage;
    private Timestamp createdAt;
}
