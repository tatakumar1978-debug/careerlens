package com.oop.resumeanalyzer.service;

import com.oop.resumeanalyzer.model.JobRole;

public class ManagerialReportGenerator extends ReportGenerator {

    @Override
    public String generateReport(String candidateName, JobRole role, AnalysisResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append(formatHeader(candidateName, role));
        sb.append("Matched Competencies : ").append(join(result.getMatchedSkills())).append("\n");
        sb.append("Gap Areas            : ").append(join(result.getMissingSkills())).append("\n");
        sb.append("Leadership Fit %     : ").append(result.getMatchPercentage()).append("%\n");

        if (result.getMatchPercentage() >= 75) {
            sb.append("Recommendation       : Ready for people-management responsibilities.\n");
        } else {
            sb.append("Recommendation       : Develop ")
                    .append(join(result.getMissingSkills()))
                    .append(" through leadership training before applying.\n");
        }
        sb.append("===================================");
        return sb.toString();
    }

    private String join(java.util.List<String> items) {
        return items.isEmpty() ? "None" : String.join(", ", items);
    }
}
