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

    /**
     * Creates a new entry.
     * @param workLogEntry
     * @return
     */
    @PostMapping
    public WorkLogEntry createEntry(@RequestBody WorkLogEntry workLogEntry){
        return workLogService.create(workLogEntry);
    }

    /**
     * Deletes an entry.
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Long id){
        workLogService.deleteById(id);
    }

    /**
     * Gets all the entries.
     * @return
     */
    @GetMapping
    List<WorkLogEntry> getEntries(){
        return workLogService.getAllEntries();
    }

}
