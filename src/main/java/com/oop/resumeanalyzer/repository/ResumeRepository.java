package com.oop.resumeanalyzer.repository;

import com.oop.resumeanalyzer.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
