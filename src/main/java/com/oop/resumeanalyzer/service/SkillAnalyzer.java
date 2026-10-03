package com.oop.resumeanalyzer.service;

import com.oop.resumeanalyzer.model.JobRole;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * SkillAnalyzer (Module 3)
 * ------------------------
 * Concrete implementation of Analyzer. Uses List/Set-style
 * retainAll()/removeAll() logic (case-insensitive) to compute matched
 * and missing skills, exactly as described on the "Skill Analysis" slide.
 *
 * Formula: Matched Skills / Required Skills * 100
 */
@Component
public class SkillAnalyzer extends Analyzer {

    @Override
    public AnalysisResult analyze(List<String> candidateSkills, JobRole jobRole) {
        List<String> required = jobRole.getRequiredSkills();

        List<String> candidateLower = new ArrayList<>();
        for (String s : candidateSkills) {
            candidateLower.add(s.trim().toLowerCase());
        }

        List<String> matched = new ArrayList<>();
        List<String> missing = new ArrayList<>();

        for (String reqSkill : required) {
            if (candidateLower.contains(reqSkill.trim().toLowerCase())) {
                matched.add(reqSkill);
            } else {
                missing.add(reqSkill);
            }
        }

        double percentage = required.isEmpty()
                ? 0.0
                : (matched.size() * 100.0) / required.size();

        return new AnalysisResult(matched, missing, Math.round(percentage * 100.0) / 100.0);
    }
}
