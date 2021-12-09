package com.auth.server.model;

import com.auth.server.util.AuthorizationGrantTypes;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "client_grant_type")
public class ClientGrantType {

    @Id
    private String id;
    private String clientId;
    @Enumerated(value = EnumType.STRING)
    private AuthorizationGrantTypes grantType;

    @PrePersist
    public void setId(){
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public AuthorizationGrantTypes getGrantType() {
        return grantType;
    }

    public void setGrantType(AuthorizationGrantTypes grantType) {
        this.grantType = grantType;
    }
}
