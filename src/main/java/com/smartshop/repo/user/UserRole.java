package com.smartshop.repo.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="user_role")
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
