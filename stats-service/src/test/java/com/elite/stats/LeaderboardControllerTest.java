package com.elite.stats;

import com.elite.stats.config.SecurityConfig;
import com.elite.stats.controller.LeaderboardController;
import com.elite.stats.service.RunClient;
import com.elite.stats.service.ScoringService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LeaderboardController.class)
@Import({SecurityConfig.class, ScoringService.class})
class LeaderboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RunClient runClient;

    @Test
    void shouldReturnLeaderboardSortedByFastestTime() throws Exception {
        given(runClient.fetchRuns()).willReturn(List.of(
                new RunClient.RunSubmission("riven", "Nightfall", "Glass Gate", "001", "Sprint", 154321L),
                new RunClient.RunSubmission("alex", "Apex Run", "Harbor Run", "000", "Sprint", 123456L)
        ));

        mockMvc.perform(get("/api/stats/leaderboard"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].playerName").value("alex"))
                .andExpect(jsonPath("$[0].timeMs").value(123456))
                .andExpect(jsonPath("$[1].playerName").value("riven"));
    }
}
