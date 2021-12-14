package com.auth.server.controller;

import com.auth.server.model.ClientDetailsModel;
import com.auth.server.model.UserDetailsModel;
import com.auth.server.util.ApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface CredentialsController {

    @GetMapping("/user/details")
    ResponseEntity<UserDetailsModel> getUserDetails(String username) throws ApplicationException;

    @PostMapping("/user/details")
    ResponseEntity<UserDetailsModel> onBoardCredentials(UserDetailsModel userDetails) throws ApplicationException;

    @PostMapping("/client/details")
    ResponseEntity<ClientDetailsModel> onBoardClientCredentials(ClientDetailsModel clientDetails) throws ApplicationException;

    @PostMapping("/resource/details")
    ResponseEntity<ClientDetailsModel> onBoardResourceApp(String clientId, String resourceId) throws ApplicationException;
}