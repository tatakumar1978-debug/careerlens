package com.oop.resumeanalyzer.repository;

import com.oop.resumeanalyzer.model.JobRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRoleRepository extends JpaRepository<JobRole, Long> {
}
