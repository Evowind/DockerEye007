package com.elite.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/services")
    public ServiceStatus getServices() {
        return new ServiceStatus(
                "auth-service",
                "http://localhost:8081/actuator/health",
                "run-service",
                "http://localhost:8082/actuator/health",
                "stats-service",
                "http://localhost:8083/actuator/health"
        );
    }

    public record ServiceStatus(
            String authService,
            String authHealth,
            String runService,
            String runHealth,
            String statsService,
            String statsHealth
    ) {
    }
}
