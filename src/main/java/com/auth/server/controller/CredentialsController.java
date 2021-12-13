package com.auth.server.controller;

import com.auth.server.model.ClientDetailsModel;
import com.auth.server.model.UserDetailsModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface CredentialsController {

    @GetMapping("/user/details")
    ResponseEntity<UserDetailsModel> getUserDetails(String username);

    @PostMapping("/user/details")
    ResponseEntity<UserDetailsModel> onBoardCredentials(UserDetailsModel userDetails);

    @PostMapping("/client/details")
    ResponseEntity<ClientDetailsModel> onBoardClientCredentials(ClientDetailsModel clientDetails);

    @PostMapping("/resource/details")
    ResponseEntity<ClientDetailsModel> onBoardResourceApp(String clientId, String resourceId);
}