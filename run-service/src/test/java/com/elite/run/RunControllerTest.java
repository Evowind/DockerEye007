package com.elite.run;

import com.elite.run.controller.RunController;
import com.elite.run.model.RunSubmission;
import com.elite.run.repository.RunSubmissionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.elite.run.config.SecurityConfig;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RunController.class)
@Import(SecurityConfig.class)
class RunControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RunSubmissionRepository runSubmissionRepository;

    @Test
    void shouldSubmitRun() throws Exception {
        RunSubmission run = new RunSubmission();
        run.setId(1L);
        run.setPlayerName("alex");
        run.setGame("Apex Run");
        run.setStageName("Harbor Run");
        run.setCategory("000");
        run.setDifficulty("Sprint");
        run.setTimeMs(123456L);

        given(runSubmissionRepository.save(any(RunSubmission.class))).willReturn(run);

        mockMvc.perform(post("/api/runs/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(run)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerName").value("alex"));
    }

    @Test
    void shouldListRuns() throws Exception {
        given(runSubmissionRepository.findAll()).willReturn(List.of(new RunSubmission("alex", "Apex Run", "Harbor Run", "000", "Sprint", 98765L)));

        mockMvc.perform(get("/api/runs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].playerName").value("alex"));
    }
}
