package com.auth.server.config;

import com.auth.server.model.UserRoles;
import org.springframework.security.core.GrantedAuthority;
import com.auth.server.model.UserDetailsModel;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class UserDetailsImpl implements org.springframework.security.core.userdetails.UserDetails {

    private UserDetailsModel userDetails;
    private UserRoles userRoles;

    public UserDetailsImpl() {
        super();
    }

    public UserDetailsImpl(UserDetailsModel userDetails, UserRoles userRoles){
        this.userDetails = userDetails;
        this.userRoles = userRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRoles.getRole()));
    }

    @Override
    public String getPassword() {
        return this.userDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userDetails.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.userDetails.isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.userDetails.isEnabled();
    }
}
