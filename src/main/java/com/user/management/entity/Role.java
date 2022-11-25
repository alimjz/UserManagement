package com.user.management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_ROLE")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private String roleName;



    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long umRoleId) {
        this.roleId = umRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String umRoleTitle) {
        this.roleName = umRoleTitle;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
