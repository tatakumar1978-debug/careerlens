package com.oop.resumeanalyzer.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

/**
 * TechnicalRole extends JobRole -> INHERITANCE.
 * e.g. Software Developer, Frontend Developer.
 */
@Entity
@DiscriminatorValue("TECHNICAL")
public class TechnicalRole extends JobRole {

    private String techStack;

    public TechnicalRole() {
        super();
    }

    public TechnicalRole(String roleName, List<String> requiredSkills, String techStack) {
        super(roleName, requiredSkills);
        this.techStack = techStack;
    }

    @Override
    public String getRoleCategory() {
        return "Technical";
    }

    public String getTechStack() {
        return techStack;
    }

    public void setTechStack(String techStack) {
        this.techStack = techStack;
    }
}
