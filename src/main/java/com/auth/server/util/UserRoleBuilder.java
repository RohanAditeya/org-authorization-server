package com.auth.server.util;

import com.auth.server.model.UserRoles;
import org.springframework.stereotype.Component;

@Component
public class UserRoleBuilder {

    private UserRoles userRoles;

    public UserRoleBuilder () {
        this.userRoles = new UserRoles();
    }

    public UserRoles build() {
        return this.userRoles;
    }

    public UserRoleBuilder setUserDetails(String username) {
        userRoles.setUsername(username);
        return this;
    }

    public UserRoleBuilder setRole(String role) {
        this.userRoles.setRole(role);
        return this;
    }
}
