package com.auth.server.model;

import com.datastax.oss.driver.api.mapper.annotations.*;
import com.datastax.oss.driver.api.mapper.entity.naming.NamingConvention;

@Entity
@CqlName(value = "client_details")
@NamingStrategy(convention = NamingConvention.SNAKE_CASE_INSENSITIVE)
public class ClientDetailsModel {

    @PartitionKey
    private String clientId;
    @ClusteringColumn(value = 0)
    private String resourceId;
    @ClusteringColumn(value = 1)
    private String grantType;
    private String secret;
    private String scope;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
