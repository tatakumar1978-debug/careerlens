package com.oop.resumeanalyzer.service;

import java.util.List;

/**
 * Holds the outcome of an Analyzer.analyze() call:
 * matched skills, missing skills and the compatibility percentage.
 */
public class AnalysisResult {

    private final List<String> matchedSkills;
    private final List<String> missingSkills;
    private final double matchPercentage;

    public AnalysisResult(List<String> matchedSkills, List<String> missingSkills, double matchPercentage) {
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
        this.matchPercentage = matchPercentage;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }
}
