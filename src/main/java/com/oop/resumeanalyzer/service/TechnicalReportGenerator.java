package com.oop.resumeanalyzer.service;

import com.oop.resumeanalyzer.model.JobRole;

public class TechnicalReportGenerator extends ReportGenerator {

    @Override
    public String generateReport(String candidateName, JobRole role, AnalysisResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append(formatHeader(candidateName, role));
        sb.append("Matched Skills   : ").append(join(result.getMatchedSkills())).append("\n");
        sb.append("Missing Skills   : ").append(join(result.getMissingSkills())).append("\n");
        sb.append("Skill Match %    : ").append(result.getMatchPercentage()).append("%\n");

        if (result.getMatchPercentage() >= 75) {
            sb.append("Recommendation   : Strong fit. Polish your projects and apply.\n");
        } else if (result.getMatchPercentage() >= 40) {
            sb.append("Recommendation   : Focus on ")
                    .append(join(result.getMissingSkills()))
                    .append(" before applying.\n");
        } else {
            sb.append("Recommendation   : Build foundational skills - consider an internship or bootcamp first.\n");
        }
        sb.append("===================================");
        return sb.toString();
    }

    private String join(java.util.List<String> items) {
        return items.isEmpty() ? "None" : String.join(", ", items);
    }
}
