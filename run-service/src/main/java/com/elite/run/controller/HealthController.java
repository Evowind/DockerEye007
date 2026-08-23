package com.elite.run.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/runs")
public class HealthController {

    @GetMapping("/health")
    public ServiceHealth health() {
        return new ServiceHealth("run-service", "UP");
    }

    public record ServiceHealth(String service, String status) {
    }
}
