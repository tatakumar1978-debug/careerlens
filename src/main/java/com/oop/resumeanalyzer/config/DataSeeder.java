package com.oop.resumeanalyzer.config;

import com.oop.resumeanalyzer.model.ManagerialRole;
import com.oop.resumeanalyzer.model.TechnicalRole;
import com.oop.resumeanalyzer.repository.JobRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Seeds the job_role table with sample TechnicalRole and ManagerialRole
 * objects the first time the app runs against an empty database.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final JobRoleRepository jobRoleRepository;

    public DataSeeder(JobRoleRepository jobRoleRepository) {
        this.jobRoleRepository = jobRoleRepository;
    }

    @Override
    public void run(String... args) {
        if (jobRoleRepository.count() > 0) {
            return;
        }

        jobRoleRepository.save(new TechnicalRole(
                "Software Developer",
                List.of("Java", "SQL", "Git", "Data Structures", "OOP"),
                "Java / Spring"));

        jobRoleRepository.save(new TechnicalRole(
                "Frontend Developer",
                List.of("HTML", "CSS", "JavaScript", "React", "Git"),
                "React / JS"));

        jobRoleRepository.save(new TechnicalRole(
                "Data Analyst",
                List.of("SQL", "Python", "Excel", "Data Structures", "Statistics"),
                "Python / SQL"));

        jobRoleRepository.save(new ManagerialRole(
                "Project Manager",
                List.of("Communication", "Agile", "Risk Management", "Budgeting", "Leadership"),
                8));

        jobRoleRepository.save(new ManagerialRole(
                "Team Lead",
                List.of("Java", "Leadership", "Agile", "Communication", "Mentoring"),
                5));
    }
}
