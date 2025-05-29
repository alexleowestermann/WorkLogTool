package com.example.work_log;

import com.example.work_log.model.WorkLogEntry;
import com.example.work_log.service.WorkLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WorkLogServiceTest {

    @Autowired
    private WorkLogService workLogService;

    @Test
    void testCreateWorkLogEntry() {
        WorkLogEntry workLogEntry = new WorkLogEntry();
        workLogEntry.setTitle("Began Working on BackEnd");
        workLogEntry.setDescription("I refactored the controller layer");
        workLogEntry.setDate(LocalDate.now());
        workLogEntry.addTag("Backend");
        workLogEntry.addTag("Java");


    }

}
