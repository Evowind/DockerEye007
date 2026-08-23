package com.elite.stats.controller;

import com.elite.stats.service.RunClient;
import com.elite.stats.service.ScoringService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/stats")
public class LeaderboardController {

    private final RunClient runClient;
    private final ScoringService scoringService;

    public LeaderboardController(RunClient runClient, ScoringService scoringService) {
        this.runClient = runClient;
        this.scoringService = scoringService;
    }

    @GetMapping("/leaderboard")
    public List<LeaderboardEntry> leaderboard() {
        long worldRecord = 123456L;
        long pointlessTime = 180000L;

        return runClient.fetchRuns().stream()
                .map(run -> new LeaderboardEntry(
                        run.playerName(),
                        run.game(),
                        run.stageName(),
                        run.category(),
                        run.difficulty(),
                        run.timeMs(),
                        scoringService.calculatePoints(worldRecord, pointlessTime, run.timeMs()),
                        0L
                ))
                .sorted(Comparator.comparingLong(LeaderboardEntry::timeMs))
                .toList();
    }

    public record LeaderboardEntry(
            String playerName,
            String game,
            String stageName,
            String category,
            String difficulty,
            Long timeMs,
            Long points,
            Long rank
    ) {
    }
}
