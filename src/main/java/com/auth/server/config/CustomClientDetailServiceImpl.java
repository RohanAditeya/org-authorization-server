package com.auth.server.config;

import com.auth.server.model.ClientDetailsModel;
import com.auth.server.repository.ClientDetailsCassandraDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomClientDetailServiceImpl implements ClientDetailsService {

    @Autowired
    private ClientDetailsCassandraDao clientDetailsRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        ClientDetailsModel clientDetailsRecord = clientDetailsRepository.findByClientId(clientId);

        if(clientDetailsRecord == null)
            throw new ClientRegistrationException("Client not registered");
        return new CustomClientDetailsImpl(clientDetailsRecord);
    }
}
