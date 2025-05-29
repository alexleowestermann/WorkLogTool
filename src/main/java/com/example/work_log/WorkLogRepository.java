package com.example.work_log;

import com.example.work_log.model.WorkLogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkLogRepository extends JpaRepository<WorkLogEntry, Long> {
}
