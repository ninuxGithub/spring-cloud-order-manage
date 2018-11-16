package com.ninuxgithub.authserver.model;

public enum AuthorityName {
    ROLE_USER("ROLE_USER", "普通用户"), ROLE_ADMIN("ROLE_ADMIN", "管理员");
    private String role;

    private String roleName;

    AuthorityName(String role, String roleName) {
        this.role = role;
        this.roleName = roleName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
