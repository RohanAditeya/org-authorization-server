package com.auth.server.service;

import com.auth.server.model.ClientDetailsModel;
import com.auth.server.model.UserDetailsModel;

public interface CredentialsService {
    UserDetailsModel getUserDetailsFromDb(String username);
    void onBoardUserCredentials(UserDetailsModel userDetails);
    void onBoardClientDetails(ClientDetailsModel clientDetailsModel);
    void onBoardResourceApp(String clientId, String resourceId);
}