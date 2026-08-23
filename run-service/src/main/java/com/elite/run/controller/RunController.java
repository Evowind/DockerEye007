package com.elite.run.controller;

import com.elite.run.model.RunSubmission;
import com.elite.run.repository.RunSubmissionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunSubmissionRepository runSubmissionRepository;

    public RunController(RunSubmissionRepository runSubmissionRepository) {
        this.runSubmissionRepository = runSubmissionRepository;
    }

    @PostMapping("/submit")
    public RunSubmission submitRun(@RequestBody RunSubmission runSubmission) {
        return runSubmissionRepository.save(runSubmission);
    }

    @GetMapping
    public List<RunSubmission> listRuns() {
        return runSubmissionRepository.findAll();
    }
}
