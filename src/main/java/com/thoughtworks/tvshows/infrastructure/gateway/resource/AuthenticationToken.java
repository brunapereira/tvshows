package com.thoughtworks.tvshows.infrastructure.gateway.resource;

public class AuthenticationToken {
    private String token;

    public AuthenticationToken(String token) {
        this.token = token;
    }

    public AuthenticationToken() {
    }

    public String getToken() {
        return token;
    }
}
