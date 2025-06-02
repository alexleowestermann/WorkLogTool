package com.example.work_log.service;

import com.example.work_log.WorkLogRepository;
import com.example.work_log.model.WorkLogEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkLogService {

    @Autowired
    public WorkLogRepository workLogRepository;

    // Creates a new work entry.
    public WorkLogEntry create(WorkLogEntry entry){
        return workLogRepository.save(entry);
    }

    /**
     * Deletes the user from their id.
     * @param id
     */
    public void deleteById(Long id) {
        workLogRepository.deleteById(id);
    }

    /**
     * Gets all the entries.
     * @return
     */
    public List<WorkLogEntry> getAllEntries(){
        return workLogRepository.findAll();
    }

    /**
     * Gets an entry by its id.
     * @param id
     * @return
     */
    public Optional<WorkLogEntry> getEntryById(Long id) {
        return workLogRepository.findById(id);
    }

}
