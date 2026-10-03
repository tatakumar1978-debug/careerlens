package com.oop.resumeanalyzer.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

/**
 * ManagerialRole extends JobRole -> INHERITANCE.
 * e.g. Project Manager, Team Lead.
 */
@Entity
@DiscriminatorValue("MANAGERIAL")
public class ManagerialRole extends JobRole {

    private int teamSizeManaged;

    public ManagerialRole() {
        super();
    }

    public ManagerialRole(String roleName, List<String> requiredSkills, int teamSizeManaged) {
        super(roleName, requiredSkills);
        this.teamSizeManaged = teamSizeManaged;
    }

    @Override
    public String getRoleCategory() {
        return "Managerial";
    }

    public int getTeamSizeManaged() {
        return teamSizeManaged;
    }

    public void setTeamSizeManaged(int teamSizeManaged) {
        this.teamSizeManaged = teamSizeManaged;
    }
}
