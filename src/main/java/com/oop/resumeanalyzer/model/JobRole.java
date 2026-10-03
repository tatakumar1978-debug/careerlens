package com.oop.resumeanalyzer.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * JobRole (abstract base class)
 * ------------------------------
 * Demonstrates ABSTRACTION and is the parent of the INHERITANCE hierarchy
 * shown on the "OOP Concepts Applied" slide:
 *   JobRole  --extends-->  TechnicalRole
 *            --extends-->  ManagerialRole
 *
 * getRoleCategory() is abstract; each subclass supplies its own
 * implementation (used together with POLYMORPHISM at report-generation time).
 */
@Entity
@Table(name = "job_role")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role_type")
public abstract class JobRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    private String roleName;

    @ElementCollection
    @CollectionTable(name = "job_role_skills", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "skill")
    private List<String> requiredSkills = new ArrayList<>();

    protected JobRole() {
    }

    protected JobRole(String roleName, List<String> requiredSkills) {
        this.roleName = roleName;
        this.requiredSkills = requiredSkills;
    }

    /** Abstract method - overridden by TechnicalRole and ManagerialRole. */
    public abstract String getRoleCategory();

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }
}
