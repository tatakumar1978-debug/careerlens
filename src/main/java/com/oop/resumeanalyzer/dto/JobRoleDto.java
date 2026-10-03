package com.oop.resumeanalyzer.dto;

import java.util.List;

public class JobRoleDto {
    private Long roleId;
    private String roleName;
    private String roleCategory;
    private List<String> requiredSkills;

    public JobRoleDto(Long roleId, String roleName, String roleCategory, List<String> requiredSkills) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleCategory = roleCategory;
        this.requiredSkills = requiredSkills;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRoleCategory() {
        return roleCategory;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }
}
