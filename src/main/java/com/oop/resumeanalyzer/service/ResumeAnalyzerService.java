package com.oop.resumeanalyzer.service;

import com.oop.resumeanalyzer.dto.AnalysisRequest;
import com.oop.resumeanalyzer.dto.AnalysisResponse;
import com.oop.resumeanalyzer.model.JobRole;
import com.oop.resumeanalyzer.model.Report;
import com.oop.resumeanalyzer.model.Resume;
import com.oop.resumeanalyzer.model.User;
import com.oop.resumeanalyzer.repository.JobRoleRepository;
import com.oop.resumeanalyzer.repository.ReportRepository;
import com.oop.resumeanalyzer.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Walks through the "Working Flow" from the slides:
 * store candidate -> extract skills -> compare with job role ->
 * compute score -> identify missing skills -> generate report.
 */
@Service
public class ResumeAnalyzerService {

    private final JobRoleRepository jobRoleRepository;
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;
    private final Analyzer analyzer;               // abstraction in action
    private final ReportGeneratorFactory reportGeneratorFactory;

    public ResumeAnalyzerService(JobRoleRepository jobRoleRepository,
                                  UserRepository userRepository,
                                  ReportRepository reportRepository,
                                  SkillAnalyzer analyzer,
                                  ReportGeneratorFactory reportGeneratorFactory) {
        this.jobRoleRepository = jobRoleRepository;
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
        this.analyzer = analyzer;
        this.reportGeneratorFactory = reportGeneratorFactory;
    }

    public List<JobRole> listRoles() {
        return jobRoleRepository.findAll();
    }

    public AnalysisResponse analyze(AnalysisRequest request) {
        JobRole role = jobRoleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new NoSuchElementException("Job role not found: " + request.getRoleId()));

        // 1. Store candidate information (User + Resume)
        User user = new User(request.getName(), request.getEmail(), "", "");
        Resume resume = new Resume();
        resume.setSkills(request.getSkills());
        resume.setUser(user);
        user.setResume(resume);
        userRepository.save(user);

        // 2. Compare candidate skills vs job role (Analyzer abstraction -> SkillAnalyzer)
        AnalysisResult result = analyzer.analyze(request.getSkills(), role);

        // 3. Generate report (ReportGenerator polymorphism, chosen via factory)
        ReportGenerator generator = reportGeneratorFactory.getGenerator(role);
        String reportText = generator.generateReport(request.getName(), role, result);

        // 4. Persist the report
        Report report = new Report(request.getName(), role.getRoleName(), result.getMatchPercentage(), reportText);
        reportRepository.save(report);

        return new AnalysisResponse(
                request.getName(),
                role.getRoleName(),
                role.getRoleCategory(),
                result.getMatchedSkills(),
                result.getMissingSkills(),
                result.getMatchPercentage(),
                reportText
        );
    }

    public List<Report> recentReports() {
        return reportRepository.findAllByOrderByGeneratedAtDesc();
    }
}
