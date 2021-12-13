package com.auth.server.model;

import com.auth.server.util.ConstantValidation;
import com.datastax.oss.driver.api.mapper.annotations.*;
import com.datastax.oss.driver.api.mapper.entity.naming.NamingConvention;

import javax.validation.constraints.NotNull;

import static com.auth.server.util.Constants.*;

@Entity
@CqlName(value = "client_details")
@NamingStrategy(convention = NamingConvention.SNAKE_CASE_INSENSITIVE)
public class ClientDetailsModel {

    @PartitionKey
    private String clientId;
    @ClusteringColumn(value = 0)
    private String resourceId;
    @ClusteringColumn(value = 1)
    @ConstantValidation(allowedConstants = {AUTHORIZATION_CODE, PASSWORD, REFRESH_TOKEN, CLIENT_CREDENTIALS},
            message = "grant type can take only values {'authorization_code','password','refresh_token','client_credentials'}")
    private String grantType;
    @NotNull(message = "client-secret cannot be null")
    private String secret;
    @ConstantValidation(allowedConstants = {READ, WRITE}, message = "scope can take only values {'READ','WRITE'}")
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
