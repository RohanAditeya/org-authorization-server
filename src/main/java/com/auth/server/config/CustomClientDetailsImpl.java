package com.auth.server.config;

import com.auth.server.model.ClientDetailsModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CustomClientDetailsImpl implements ClientDetails {

    private ClientDetailsModel clientDetails;

    public CustomClientDetailsImpl() {
        super();
    }

    public CustomClientDetailsImpl(ClientDetailsModel clientDetails) {
        this.clientDetails = clientDetails;
    }

    @Override
    public String getClientId() {
        return clientDetails.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        return this.clientDetails.getResourceId() != null?
                Set.of(clientDetails.getResourceId()):
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
        return Set.of(clientDetails.getGrantType());
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
