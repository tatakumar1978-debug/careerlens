package com.oop.resumeanalyzer.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Resume (Module 2)
 * -----------------
 * Stores qualification, skills, projects, certifications and experience
 * for a candidate. Private fields + public accessors = Encapsulation.
 */
@Entity
@Table(name = "resume")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resumeId;

    private String qualification;
    private String experience;

    @ElementCollection
    @CollectionTable(name = "resume_skills", joinColumns = @JoinColumn(name = "resume_id"))
    @Column(name = "skill")
    private List<String> skills = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "resume_projects", joinColumns = @JoinColumn(name = "resume_id"))
    @Column(name = "project")
    private List<String> projects = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "resume_certifications", joinColumns = @JoinColumn(name = "resume_id"))
    @Column(name = "certification")
    private List<String> certifications = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Resume() {
    }

    public Resume(String qualification, List<String> skills, String experience) {
        this.qualification = qualification;
        this.skills = skills;
        this.experience = experience;
    }

    // ---- addSkill() / addProject() / getSkills() from the design slide ----
    public void addSkill(String skill) {
        this.skills.add(skill);
    }

    public void addProject(String project) {
        this.projects.add(project);
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }

    public List<String> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<String> certifications) {
        this.certifications = certifications;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
