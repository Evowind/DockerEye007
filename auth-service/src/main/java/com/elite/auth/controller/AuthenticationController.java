package com.elite.auth.controller;

import com.elite.auth.model.User;
import com.elite.auth.repository.UserRepository;
import com.elite.auth.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthenticationController(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {
        User saved = userRepository.save(user);
        String token = jwtService.generateToken(saved.getUsername());
        return ResponseEntity.ok(Map.of(
                "id", saved.getId(),
                "username", saved.getUsername(),
                "email", saved.getEmail(),
                "country", saved.getCountry(),
                "token", token
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        User user = userRepository.findAll().stream()
                .filter(current -> current.getUsername().equals(loginRequest.username()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        String token = jwtService.generateToken(user.getUsername());
        return ResponseEntity.ok(Map.of(
                "username", user.getUsername(),
                "token", token
        ));
    }

    public record LoginRequest(String username) {
    }
}
