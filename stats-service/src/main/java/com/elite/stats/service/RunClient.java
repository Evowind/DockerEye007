package com.elite.stats.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RunClient {

    private final RestClient restClient;

    public RunClient() {
        this.restClient = RestClient.builder()
                .baseUrl("http://run-service:8082")
                .build();
    }

    public List<RunSubmission> fetchRuns() {
        RunSubmission[] runs = restClient.get()
                .uri("/api/runs")
                .retrieve()
                .body(RunSubmission[].class);

        return runs == null ? List.of() : List.of(runs);
    }

    public record RunSubmission(String playerName, String game, String stageName, String category, String difficulty, Long timeMs) {
    }
}
