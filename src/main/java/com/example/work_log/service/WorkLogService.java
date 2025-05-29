package com.example.work_log.service;

import com.example.work_log.WorkLogRepository;
import com.example.work_log.model.WorkLogEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class WorkLogService {

    @Autowired
    public WorkLogRepository workLogRepository;

    public WorkLogEntry create(WorkLogEntry entry){
        return workLogRepository.save(entry);
    }

    public List<WorkLogEntry> getAllEntries(){
        return workLogRepository.findAll();
    }

}
