package com.auth.server.config;

import com.auth.server.model.ClientDetailsModel;
import com.auth.server.model.ClientGrantType;
import com.auth.server.model.ClientResourceModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CustomClientDetailsImpl implements ClientDetails {

    private ClientDetailsModel clientDetails;
    private List<ClientGrantType> clientGrantType;
    private List<ClientResourceModel> clientResource;

    public CustomClientDetailsImpl() {
        super();
    }

    public CustomClientDetailsImpl(ClientDetailsModel clientDetails, List<ClientGrantType> clientGrantType) {
        this.clientDetails = clientDetails;
        this.clientGrantType = clientGrantType;
    }

    public CustomClientDetailsImpl(ClientDetailsModel clientDetails, List<ClientGrantType> clientGrantType, List<ClientResourceModel> clientResource) {
        this(clientDetails, clientGrantType);
        this.clientResource = clientResource;
    }

    @Override
    public String getClientId() {
        return clientDetails.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        return this.clientResource != null?
                clientResource.stream().map(clientResourceModel -> clientResourceModel.getResourceId()).collect(Collectors.toSet()):
                null;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return clientDetails.getSecret();
    }

    @Override
    public boolean isScoped() {
        return clientDetails.getScope() != null;
    }

    @Override
    public Set<String> getScope() {
        return Set.of(clientDetails.getScope());
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return clientGrantType.stream()
                .map(clientGrantTypeRecord -> clientGrantTypeRecord.getGrantType().getGrantType())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return Collections.emptySet();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.emptySet();
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return 3600;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return null;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
