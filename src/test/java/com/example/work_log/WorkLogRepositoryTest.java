package com.example.work_log;

import com.example.work_log.model.WorkLogEntry;
import com.example.work_log.WorkLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WorkLogRepositoryTest {

    @Autowired
    private WorkLogRepository repository;

    /**
     * Tests saving an entry and deleting it/
     */
    @Test
    void testSaveAndDelete() {
        // Create a new entry
        WorkLogEntry entry = new WorkLogEntry();
        entry.setTitle("Repo Test Entry");
        entry.setDescription("Testing the repository layer");
        entry.setDate(LocalDate.now());

        WorkLogEntry savedEntry = repository.save(entry);
        assertThat(savedEntry.getId()).isNotNull();

        // Check it's in the list
        List<WorkLogEntry> entries = repository.findAll();
        assertThat(entries).extracting(WorkLogEntry::getTitle)
                .contains("Repo Test Entry");

        // Delete the entry
        repository.deleteById(savedEntry.getId());
        assertThat(repository.findById(savedEntry.getId())).isEmpty();
    }
}