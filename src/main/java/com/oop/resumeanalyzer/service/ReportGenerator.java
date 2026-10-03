package com.oop.resumeanalyzer.service;

import com.oop.resumeanalyzer.model.JobRole;

/**
 * ReportGenerator (abstract)
 * --------------------------
 * generateReport() is OVERRIDDEN differently for each report type
 * (TechnicalReportGenerator vs ManagerialReportGenerator) -> POLYMORPHISM,
 * exactly as described on the "OOP Concepts Applied" slide.
 */
public abstract class ReportGenerator {

    public abstract String generateReport(String candidateName, JobRole role, AnalysisResult result);

    /** Shared helper reused by every subclass. */
    protected String formatHeader(String candidateName, JobRole role) {
        return "===== CAREER ANALYSIS REPORT =====\n" +
                "Candidate     : " + candidateName + "\n" +
                "Target Role   : " + role.getRoleName() + " (" + role.getRoleCategory() + ")\n";
    }
}
