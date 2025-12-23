package com.example.demo.controller;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fill-records")
public class FillLevelRecordController {

    private final FillLevelRecordService fillLevelRecordService;

    public FillLevelRecordController(FillLevelRecordService fillLevelRecordService) {
        this.fillLevelRecordService = fillLevelRecordService;
    }

    @PostMapping
    public FillLevelRecord create(@RequestBody FillLevelRecord record) {
        return fillLevelRecordService.createRecord(record);
    }

    @GetMapping("/{id}")
    public FillLevelRecord getById(@PathVariable Long id) {
        return fillLevelRecordService.getRecordById(id);
    }

    @GetMapping("/bin/{binId}")
    public List<FillLevelRecord> getForBin(@PathVariable Long binId) {
        return fillLevelRecordService.getRecordsForBin(binId);
    }
}
