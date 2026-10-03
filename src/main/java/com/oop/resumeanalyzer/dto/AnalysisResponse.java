package com.oop.resumeanalyzer.dto;

import java.util.List;

public class AnalysisResponse {

    private String candidateName;
    private String roleName;
    private String roleCategory;
    private List<String> matchedSkills;
    private List<String> missingSkills;
    private double matchPercentage;
    private String reportText;

    public AnalysisResponse() {
    }

    public AnalysisResponse(String candidateName, String roleName, String roleCategory,
                             List<String> matchedSkills, List<String> missingSkills,
                             double matchPercentage, String reportText) {
        this.candidateName = candidateName;
        this.roleName = roleName;
        this.roleCategory = roleCategory;
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
        this.matchPercentage = matchPercentage;
        this.reportText = reportText;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCategory() {
        return roleCategory;
    }

    public void setRoleCategory(String roleCategory) {
        this.roleCategory = roleCategory;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(List<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
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
}
