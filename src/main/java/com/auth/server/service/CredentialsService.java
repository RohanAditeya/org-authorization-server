package com.auth.server.service;

import com.auth.server.model.ClientDetailsModel;
import com.auth.server.model.ClientResourceModel;
import com.auth.server.model.UserDetailsModel;
import com.auth.server.util.AuthorizationGrantTypes;

public interface CredentialsService {
    UserDetailsModel getUserDetailsFromDb(String username);
    UserDetailsModel onBoardUserCredentials(UserDetailsModel userDetails, String role);
    ClientDetailsModel onBoardClientDetails(ClientDetailsModel clientDetailsModel, AuthorizationGrantTypes clientGrantType);
    ClientResourceModel onBoardResourceApp(ClientResourceModel clientResourceModel);
}
