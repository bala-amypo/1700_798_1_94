package com.example.demo.repository;

import com.example.demo.model.UsagePatternModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsagePatternModelRepository extends JpaRepository<UsagePatternModel, Long> {
    List<UsagePatternModel> findByBinIdOrderByCreatedAtDesc(Long binId);
}
