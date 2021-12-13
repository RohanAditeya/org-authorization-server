package com.auth.server.controller;

import com.auth.server.model.ClientDetailsModel;
import com.auth.server.model.UserDetailsModel;
import com.auth.server.service.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        UserDetailsModel record = credentialsService.getUserDetailsFromDb(username);
        return ResponseEntity.ok(record);
    }

    @Override
    public ResponseEntity<UserDetailsModel> onBoardCredentials(@RequestBody UserDetailsModel userDetails) {
        credentialsService.onBoardUserCredentials(userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<ClientDetailsModel> onBoardClientCredentials(@RequestBody ClientDetailsModel clientDetails) {
        credentialsService.onBoardClientDetails(clientDetails);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<ClientDetailsModel> onBoardResourceApp(@RequestHeader String clientId, @RequestHeader String resourceId) {
        credentialsService.onBoardResourceApp(clientId, resourceId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
