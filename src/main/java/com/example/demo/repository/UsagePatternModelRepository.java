package com.example.demo.repository;

import java.util.List;   // ⭐ ADD THIS
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.UsagePatternModel;

public interface UsagePatternModelRepository
        extends JpaRepository<UsagePatternModel, Long> {

    List<UsagePatternModel> findByBinId(Long binId);
}
