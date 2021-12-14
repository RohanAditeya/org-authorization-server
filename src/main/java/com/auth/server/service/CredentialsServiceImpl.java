package com.auth.server.service;

import com.auth.server.model.ClientDetailsModel;
import com.auth.server.model.UserDetailsModel;
import com.auth.server.repository.ClientDetailsCassandraDao;
import com.auth.server.repository.UserDetailsCassandraDao;
import com.auth.server.util.ApplicationException;
import com.datastax.oss.driver.api.core.cql.ResultSet;
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
    public UserDetailsModel getUserDetailsFromDb(String username) throws ApplicationException {
        UserDetailsModel record = userDetailsCassandraDao.findByUser(username);
        if (record == null)
            throw new ApplicationException("Requested username not found");
        return record;
    }

    @Override
    public void onBoardUserCredentials(UserDetailsModel userDetails) throws ApplicationException {
        String BcryptPassword = passwordEncoder.encode(userDetails.getPassword());
        userDetails.setPassword(BcryptPassword);
        ResultSet rs = userDetailsCassandraDao.save(userDetails);
        if (!rs.wasApplied())
            throw new ApplicationException("User record already exists");
    }

    @Override
    public void onBoardClientDetails(ClientDetailsModel clientDetailsModel) throws ApplicationException {
        String BcryptPassword = passwordEncoder.encode(clientDetailsModel.getSecret());
        clientDetailsModel.setSecret(BcryptPassword);
        ResultSet rs = clientDetailsCassandraDao.save(clientDetailsModel);
        if (!rs.wasApplied())
            throw new ApplicationException("Client record already exists");
    }

    @Override
    public void onBoardResourceApp(String clientId, String resourceId) throws ApplicationException {
        ClientDetailsModel record = clientDetailsCassandraDao.findByClientId(clientId);
        if (record == null)
            throw new ApplicationException("Client not found, cannot add resource");
        record.setResourceId(resourceId);
        clientDetailsCassandraDao.save(record);
    }
}
