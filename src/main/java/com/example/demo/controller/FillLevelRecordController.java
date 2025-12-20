package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.service.FillLevelRecordService;

@RestController
@RequestMapping("/api/fill-records")
public class FillLevelRecordController {

    private final FillLevelRecordService recordService;

    @Autowired
    public FillLevelRecordController(FillLevelRecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    public FillLevelRecord createRecord(@RequestBody FillLevelRecord record) {
        return recordService.createRecord(record);
    }

    @GetMapping("/{id}")
    public FillLevelRecord getRecord(@PathVariable Long id) {
        return recordService.getRecordById(id);
    }

    @GetMapping("/bin/{binId}")
    public List<FillLevelRecord> getRecordsForBin(@PathVariable Long binId) {
        return recordService.getRecordsForBin(binId);
    }

    @GetMapping("/bin/{binId}/recent")
    public List<FillLevelRecord> getRecentRecords(
            @PathVariable Long binId,
            @RequestParam int limit) {
        return recordService.getRecentRecords(binId, limit);
    }
}
