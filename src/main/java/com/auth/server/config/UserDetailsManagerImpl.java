package com.auth.server.config;

import com.auth.server.model.UserDetailsModel;
import com.auth.server.model.UserRoles;
import com.auth.server.repository.UserDetailsRepository;
import com.auth.server.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsManagerImpl implements UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetailsModel> userDetails = userDetailsRepository.findById(username);
        Optional<UserRoles> userRoles = userRolesRepository.findById(username);
        if (userDetails.isEmpty() || userRoles.isEmpty())
            throw new UsernameNotFoundException("Incorrect Username or Password");
        return new UserDetailsImpl(userDetails.get(), userRoles.get());
    }
}
