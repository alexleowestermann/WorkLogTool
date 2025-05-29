package com.example.work_log.controller;

import com.example.work_log.WorkLogRepository;
import com.example.work_log.model.WorkLogEntry;
import com.example.work_log.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class WorkLogController {

    @Autowired
    public WorkLogService workLogService;

    @PostMapping
    public WorkLogEntry createEntry(@RequestBody WorkLogEntry workLogEntry){
        return workLogService.create(workLogEntry);
    }

    @GetMapping
    List<WorkLogEntry> getEntries(){
        return workLogService.getAllEntries();
    }

}
