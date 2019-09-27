package com.tvshows.infrastructure.gateway.resource;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationToken {
    private String token;

    public String getToken() {
        return token;
    }
}
