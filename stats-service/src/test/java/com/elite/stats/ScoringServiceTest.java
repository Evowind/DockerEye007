package com.elite.stats;

import com.elite.stats.service.ScoringService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoringServiceTest {

    private final ScoringService scoringService = new ScoringService();

    @Test
    void shouldAwardMaximumPointsToWorldRecord() {
        long points = scoringService.calculatePoints(120000L, 180000L, 120000L);
        assertThat(points).isEqualTo(100L);
    }

    @Test
    void shouldReturnZeroPointsAtOrBeyondPointlessTime() {
        long points = scoringService.calculatePoints(120000L, 180000L, 180000L);
        assertThat(points).isZero();
    }

    @Test
    void shouldReducePointsAsTimeMovesAwayFromWorldRecord() {
        long points = scoringService.calculatePoints(120000L, 180000L, 150000L);
        assertThat(points).isGreaterThan(0L);
        assertThat(points).isLessThan(100L);
    }
}
