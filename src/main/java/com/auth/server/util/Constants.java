package com.auth.server.util;

public enum Constants {

    AUTHORIZATION_CODE("authorization_code"),
    PASSWORD("password"),
    REFRESH_TOKEN("refresh_token"),
    CLIENT_CREDENTIALS("client_credentials"),
    ADMIN("ROLE_ADMIN"),
    MANAGER("ROLE_MANAGER"),
    READ("READ"),
    WRITE("WRITE");

    private String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
