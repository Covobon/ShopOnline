package com.smartshop.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="UserRole")
@Table(name="user_role")
@IdClass(UserRole.class)
public class UserRole implements Serializable {

    /*Define fields*/
    @Id
    @Column(name="user_name")
    private String userName;

    @Id
    @Column(name="role")
    private String role;

    /*Define constructors*/
    public UserRole(String userName, String role) {
        this.userName = userName;
        this.role = role;
    }

    public UserRole() {
    }

    /*Define getters and setters*/

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /*Methods override*/

    @Override
    public String toString() {
        return "UserRole{" +
                "userName='" + userName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}