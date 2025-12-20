package com.example.demo.controller;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fill-records")
public class FillLevelRecordController {
    private final FillLevelRecordService recordService;

    public FillLevelRecordController(FillLevelRecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    public ResponseEntity<FillLevelRecord> addRecord(@RequestBody FillLevelRecord record) {
        return ResponseEntity.ok(recordService.createRecord(record));
    }

    @GetMapping("/bin/{binId}")
    public ResponseEntity<List<FillLevelRecord>> getByBin(@PathVariable Long binId) {
        return ResponseEntity.ok(recordService.getRecordsForBin(binId));
    }

    @GetMapping("/bin/{binId}/recent")
    public ResponseEntity<List<FillLevelRecord>> getRecent(@PathVariable Long binId, @RequestParam int limit) {
        return ResponseEntity.ok(recordService.getRecentRecords(binId, limit));
    }
}