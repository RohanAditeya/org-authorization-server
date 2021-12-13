package com.auth.server.service;

import com.auth.server.model.ClientDetailsModel;
import com.auth.server.model.UserDetailsModel;
import com.auth.server.util.ApplicationException;

public interface CredentialsService {
    UserDetailsModel getUserDetailsFromDb(String username) throws ApplicationException;
    void onBoardUserCredentials(UserDetailsModel userDetails) throws ApplicationException;
    void onBoardClientDetails(ClientDetailsModel clientDetailsModel) throws ApplicationException;
    void onBoardResourceApp(String clientId, String resourceId);
}