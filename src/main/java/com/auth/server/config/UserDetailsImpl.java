package com.auth.server.config;

import org.springframework.security.core.GrantedAuthority;
import com.auth.server.model.UserDetailsModel;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class UserDetailsImpl implements org.springframework.security.core.userdetails.UserDetails {

    private UserDetailsModel userDetails;

    public UserDetailsImpl() {
        super();
    }

    public UserDetailsImpl(UserDetailsModel userDetails){
        this.userDetails = userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userDetails.getRole()));
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
