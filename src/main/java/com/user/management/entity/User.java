package com.user.management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String userNationalId;
    @OneToOne
    private Role role;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long umuId) {
        this.userId = umuId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNationalId() {
        return userNationalId;
    }



    public void setUserNationalId(String userNationalId) {
        this.userNationalId = userNationalId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userNationalId='" + userNationalId + '\'' +
                ", role=" + role +
                '}';
    }
}
