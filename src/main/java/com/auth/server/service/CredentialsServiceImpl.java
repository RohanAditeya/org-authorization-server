package com.auth.server.service;

import com.auth.server.model.ClientDetailsModel;
import com.auth.server.model.ClientGrantType;
import com.auth.server.model.ClientResourceModel;
import com.auth.server.model.UserDetailsModel;
import com.auth.server.repository.*;
import com.auth.server.util.AuthorizationGrantTypes;
import com.auth.server.util.UserRoleBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class CredentialsServiceImpl implements CredentialsService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRoleBuilder userRoleBuilder;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Autowired
    private ClientDetailsRepository clientDetailsRepository;

    @Autowired
    private ClientGrantTypeRepository clientGrantTypeRepository;

    @Autowired
    private ClientResourceRepository clientResourceRepository;

    @Override
    public UserDetailsModel getUserDetailsFromDb(String username) {
        Optional<UserDetailsModel> userDetails = userDetailsRepository.findById(username);
        return userDetails.get();
    }

    @Override
    public UserDetailsModel onBoardUserCredentials(UserDetailsModel userDetails, String role) {
        String encodedPassword = passwordEncoder.encode(userDetails.getPassword());
        userDetails.setPassword(encodedPassword);
        userDetailsRepository.save(userDetails);
        userRolesRepository.save(userRoleBuilder.setRole(role).setUserDetails(userDetails.getUsername()).build());
        return userDetails;
    }

    @Override
    public ClientDetailsModel onBoardClientDetails(ClientDetailsModel clientDetailsModel, AuthorizationGrantTypes clientGrantType) {
        String encodedPassword = passwordEncoder.encode(clientDetailsModel.getSecret());
        clientDetailsModel.setSecret(encodedPassword);
        clientDetailsRepository.save(clientDetailsModel);
        ClientGrantType clientGrantTypeRecord = new ClientGrantType();
        clientGrantTypeRecord.setClientId(clientDetailsModel.getClientId());
        clientGrantTypeRecord.setGrantType(clientGrantType);
        clientGrantTypeRepository.save(clientGrantTypeRecord);
        return clientDetailsModel;
    }

    @Override
    public ClientResourceModel onBoardResourceApp(@RequestBody ClientResourceModel clientResourceModel) {
        clientResourceRepository.save(clientResourceModel);
        return clientResourceModel;
    }
}
