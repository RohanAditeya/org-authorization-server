package com.auth.server.config;

import com.auth.server.model.UserDetailsModel;
import com.auth.server.repository.UserDetailsCassandraDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsManagerImpl implements UserDetailsService {

    @Autowired
    private UserDetailsCassandraDao userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsModel userDetails = userDetailsRepository.findByUser(username);
        if (userDetails == null)
            throw new UsernameNotFoundException("Incorrect Username or Password");
        return new UserDetailsImpl(userDetails);
    }
}
