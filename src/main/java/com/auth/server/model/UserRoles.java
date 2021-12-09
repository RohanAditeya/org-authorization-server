package com.auth.server.model;

import javax.persistence.*;

@Entity(name = "user_roles")
public class UserRoles {

    @Id
    private String username;

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
