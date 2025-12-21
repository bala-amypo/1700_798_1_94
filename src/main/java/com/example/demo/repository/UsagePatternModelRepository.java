package com.example.demo.repository;

import com.example.demo.model.UsagePatternModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsagePatternRepository
        extends JpaRepository<UsagePatternModel, Long> {
}
