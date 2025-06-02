package com.example.work_log;

import com.example.work_log.model.WorkLogEntry;
import com.example.work_log.service.WorkLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WorkLogServiceTest {

    @Autowired
    private WorkLogService workLogService;

    /**
     * Tests creating a new entry.
     */
    @Test
    void testCreateWorkLogEntry() {
        WorkLogEntry workLogEntry = new WorkLogEntry();
        workLogEntry.setTitle("Began Working on BackEnd");
        workLogEntry.setDescription("I refactored the controller layer");
        workLogEntry.setDate(LocalDate.now());
        workLogEntry.addTag("Backend");
        workLogEntry.addTag("Java");
    }

    /**
     * Tests deleting an entry.
     */
    @Test
    void testDeleteWorkLogEntry() {
        WorkLogEntry entry = new WorkLogEntry();
        entry.setTitle("Title");
        entry.setDescription("Description Goes Here");
        entry.setDate(LocalDate.now());
        WorkLogEntry savedEntry = workLogService.create(entry);

        // Delete the entry.
        workLogService.deleteById(entry.getId());

        // Check that it's been deleted.
        Optional<WorkLogEntry> deletedEntry = workLogService.getEntryById(savedEntry.getId());
        assertTrue(deletedEntry.isEmpty());
    }

    /**
     * Tests getting all entries.
     */
    @Test
    void testGetAllEntries() {
        WorkLogEntry entry1 = new WorkLogEntry();
        entry1.setTitle("Entry 1");
        entry1.setDescription("First entry");
        entry1.setDate(LocalDate.now());

        WorkLogEntry entry2 = new WorkLogEntry();
        entry2.setTitle("Entry 2");
        entry2.setDescription("Second entry");
        entry2.setDate(LocalDate.now());

        workLogService.create(entry1);
        workLogService.create(entry2);

        List<WorkLogEntry> entries = workLogService.getAllEntries();
        assertTrue(entries.size() >= 2);
        assertTrue(entries.stream().anyMatch(e -> "Entry 1".equals(e.getTitle())));
        assertTrue(entries.stream().anyMatch(e -> "Entry 2".equals(e.getTitle())));
    }
}
