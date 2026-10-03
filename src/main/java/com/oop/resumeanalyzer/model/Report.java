package com.oop.resumeanalyzer.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Report (Module 4)
 * -----------------
 * Persists the output of ReportGenerator so past analyses can be reviewed.
 */
@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    private String candidateName;
    private String targetRole;
    private double matchPercentage;

    @Column(length = 2000)
    private String reportText;

    private LocalDateTime generatedAt;

    public Report() {
    }

    public Report(String candidateName, String targetRole, double matchPercentage, String reportText) {
        this.candidateName = candidateName;
        this.targetRole = targetRole;
        this.matchPercentage = matchPercentage;
        this.reportText = reportText;
        this.generatedAt = LocalDateTime.now();
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getTargetRole() {
        return targetRole;
    }

    public void setTargetRole(String targetRole) {
        this.targetRole = targetRole;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}
