package com.elite.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class HealthController {

    @GetMapping("/health")
    public ServiceHealth health() {
        return new ServiceHealth("auth-service", "UP");
    }

    public record ServiceHealth(String service, String status) {
    }
}
