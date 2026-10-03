package com.oop.resumeanalyzer.service;

import com.oop.resumeanalyzer.model.JobRole;

import java.util.List;

/**
 * Analyzer (ABSTRACTION)
 * ----------------------
 * Defines the contract analyze() without specifying how the comparison
 * is done. SkillAnalyzer supplies the concrete implementation.
 * This mirrors the slide: "Abstract class Analyzer defines analyze() -
 * implemented by SkillAnalyzer".
 */
public abstract class Analyzer {

    public abstract AnalysisResult analyze(List<String> candidateSkills, JobRole jobRole);
}
