package com.auth.server.config;

import com.auth.server.model.ClientDetailsModel;
import com.auth.server.model.ClientGrantType;
import com.auth.server.model.ClientResourceModel;
import com.auth.server.repository.ClientDetailsRepository;
import com.auth.server.repository.ClientGrantTypeRepository;
import com.auth.server.repository.ClientResourceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class CustomClientDetailServiceImpl implements ClientDetailsService {

    @Autowired
    private ClientDetailsRepository clientDetailsRepository;

    @Autowired
    private ClientGrantTypeRepository clientGrantTypeRepository;

    @Autowired
    private ClientResourceRepository clientResourceRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Optional<ClientDetailsModel> clientDetailsRecord = clientDetailsRepository.findById(clientId);
        Optional<List<ClientGrantType>> clientGrantTypeRecord = clientGrantTypeRepository.findByClientId(clientId);
        Optional<List<ClientResourceModel>> clientResourceRecord = clientResourceRepository.findByClientId(clientId);

        if(clientDetailsRecord.isEmpty() || clientGrantTypeRecord.isEmpty())
            throw new ClientRegistrationException("Client not registered");
        if (clientResourceRecord.isEmpty()) {
            log.warn("Client not matched with any resource applications");
            return new CustomClientDetailsImpl(clientDetailsRecord.get(), clientGrantTypeRecord.get());
        }
        return new CustomClientDetailsImpl(clientDetailsRecord.get(), clientGrantTypeRecord.get(), clientResourceRecord.get());
    }
}
