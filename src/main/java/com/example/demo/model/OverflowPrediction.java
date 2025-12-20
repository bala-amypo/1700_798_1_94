package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OverflowPrediction {
    private Bin bin;
    private int predictedFillLevel;
    private Timestamp predictedTime;
    private UsagePatternModel usagePattern;
}
