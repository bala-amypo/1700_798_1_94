package com.example.demo.repository;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.model.Bin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface FillLevelRecordRepository extends JpaRepository<FillLevelRecord, Long> {
    List<FillLevelRecord> findByBinOrderByRecordedAtDesc(Bin bin);
    Optional<FillLevelRecord> findTop1ByBinOrderByRecordedAtDesc(Bin bin);
}
