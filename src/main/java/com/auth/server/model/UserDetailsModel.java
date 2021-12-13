package com.auth.server.model;

import com.auth.server.util.ConstantValidation;
import com.auth.server.util.Constants;
import com.datastax.oss.driver.api.mapper.annotations.*;
import com.datastax.oss.driver.api.mapper.entity.naming.NamingConvention;

import javax.validation.constraints.NotNull;


@Entity
@CqlName(value = "user_details")
@NamingStrategy(convention = NamingConvention.SNAKE_CASE_INSENSITIVE)
public class UserDetailsModel {

    @PartitionKey
    private String username;
    @ClusteringColumn
    @CqlName(value = "roles")
    @ConstantValidation(allowedConstants = {Constants.ADMIN, Constants.MANAGER}, message = "Role can take only one of {'ROLE_ADMIN','ROLE_MANAGER'} values")
    private String role;
    @NotNull(message = "user password cannot be null")
    private String password;
    private boolean enabled;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
