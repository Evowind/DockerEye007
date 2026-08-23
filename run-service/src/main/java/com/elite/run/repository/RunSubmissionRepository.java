package com.elite.run.repository;

import com.elite.run.model.RunSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunSubmissionRepository extends JpaRepository<RunSubmission, Long> {
}
