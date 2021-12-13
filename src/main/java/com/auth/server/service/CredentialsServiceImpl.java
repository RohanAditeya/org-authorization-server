package com.auth.server.service;

import com.auth.server.model.ClientDetailsModel;
import com.auth.server.model.UserDetailsModel;
import com.auth.server.repository.ClientDetailsCassandraDao;
import com.auth.server.repository.UserDetailsCassandraDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CredentialsServiceImpl implements CredentialsService{

    @Autowired
    private UserDetailsCassandraDao userDetailsCassandraDao;

    @Autowired
    private ClientDetailsCassandraDao clientDetailsCassandraDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetailsModel getUserDetailsFromDb(String username) {
        UserDetailsModel record = userDetailsCassandraDao.findByUser(username);
        return record;
    }

    @Override
    public void onBoardUserCredentials(UserDetailsModel userDetails) {
        String BcryptPassword = passwordEncoder.encode(userDetails.getPassword());
        userDetails.setPassword(BcryptPassword);
        userDetailsCassandraDao.save(userDetails);
    }

    @Override
    public void onBoardClientDetails(ClientDetailsModel clientDetailsModel) {
        String BcryptPassword = passwordEncoder.encode(clientDetailsModel.getSecret());
        clientDetailsModel.setSecret(BcryptPassword);
        clientDetailsCassandraDao.save(clientDetailsModel);
    }

    @Override
    public void onBoardResourceApp(String clientId, String resourceId) {
        ClientDetailsModel record = clientDetailsCassandraDao.findByClientId(clientId);
        record.setResourceId(resourceId);
        clientDetailsCassandraDao.save(record);
    }
}
