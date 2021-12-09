package com.auth.server.controller;

import com.auth.server.model.ClientDetailsModel;
import com.auth.server.model.ClientResourceModel;
import com.auth.server.model.UserDetailsModel;
import com.auth.server.service.CredentialsService;
import com.auth.server.util.AuthorizationGrantTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CredentialsControllerImpl implements CredentialsController{

    @Autowired
    private CredentialsService credentialsService;

    @Override
    public ResponseEntity<UserDetailsModel> getUserDetails(@RequestHeader String username) {
        UserDetailsModel userDetails = credentialsService.getUserDetailsFromDb(username);
        return ResponseEntity.ok(userDetails);
    }

    @Override
    public ResponseEntity<UserDetailsModel> onBoardCredentials(@RequestBody UserDetailsModel userDetails, @RequestHeader String userRoles) {
        userDetails = credentialsService.onBoardUserCredentials(userDetails, userRoles);
        return ResponseEntity.ok(userDetails);
    }

    @Override
    public ResponseEntity<ClientDetailsModel> onBoardClientCredentials(@RequestBody ClientDetailsModel clientDetails,
                                                                       @RequestHeader AuthorizationGrantTypes clientGrantType) {
        clientDetails = credentialsService.onBoardClientDetails(clientDetails, clientGrantType);
        return ResponseEntity.ok(clientDetails);
    }

    @Override
    public ResponseEntity<ClientResourceModel> onBoardResourceApp(@RequestBody ClientResourceModel clientResourceModel) {
        clientResourceModel = credentialsService.onBoardResourceApp(clientResourceModel);
        return ResponseEntity.ok(clientResourceModel);
    }
}
