package com.example.work_log;

import com.example.work_log.model.WorkLogEntry;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class WorkLogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Tests creating an entry then getting it.
     * @throws Exception
     */
    @Test
    void testCreateAndGetEntries() throws Exception{
        WorkLogEntry entry = new WorkLogEntry();
        entry.setTitle("Title");
        entry.setDescription("Entry Description");
        entry.setDate(LocalDate.now());

        // Create entry via POST
        String entryJson = objectMapper.writeValueAsString(entry);

        mockMvc.perform(post("/api/logs")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(entryJson))
                .andExpect(status().isOk());

        // Check it's listed via GET
        mockMvc.perform(get("/api/logs"))
                .andExpect(status().isOk());
    }

    /**
     * Tests creating and deleting an entry.
     * @throws Exception
     */
    @Test
    void testCreateGetDeleteEntry() throws Exception {
        // Create a new entry
        WorkLogEntry entry = new WorkLogEntry();
        entry.setTitle("Test Entry");
        entry.setDescription("Test description");
        entry.setDate(LocalDate.now());

        String entryJson = objectMapper.writeValueAsString(entry);

        // Create entry via POST
        MvcResult postResult = mockMvc.perform(post("/api/logs")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(entryJson))
                .andExpect(status().isOk())
                .andReturn();

        // Get entry ID from the response
        String response = ((MvcResult) postResult).getResponse().getContentAsString();
        WorkLogEntry createdEntry = objectMapper.readValue(response, WorkLogEntry.class);
        Long entryId = createdEntry.getId();

        // Confirm it's in the list via GET
        mockMvc.perform(get("/api/logs"))
                .andExpect(status().isOk());

        // Delete the entry
        mockMvc.perform(delete("/api/logs/" + entryId))
                .andExpect(status().isOk());

        // Confirm it's still ok after delete
        mockMvc.perform(get("/api/logs"))
                .andExpect(status().isOk());
    }

}
