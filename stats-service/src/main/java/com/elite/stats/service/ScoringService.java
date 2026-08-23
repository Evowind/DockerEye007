package com.elite.stats.service;

import org.springframework.stereotype.Service;

@Service
public class ScoringService {

    public long calculatePoints(long worldRecordTimeMs, long pointlessTimeMs, long submittedTimeMs) {
        if (submittedTimeMs >= pointlessTimeMs) {
            return 0L;
        }

        if (submittedTimeMs <= worldRecordTimeMs) {
            return 100L;
        }

        long window = pointlessTimeMs - worldRecordTimeMs;
        long delta = submittedTimeMs - worldRecordTimeMs;

        if (window <= 0) {
            return 0L;
        }

        double normalized = (double) delta / window;
        double curve = Math.pow(normalized, 2.2);
        long points = Math.round((1.0 - curve) * 100.0);

        return Math.max(0L, Math.min(99L, points));
    }
}
