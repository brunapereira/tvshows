package com.tvshows.infrastructure.gateway.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestBody {

    private String username;
    private String userkey;
    private String apikey;
}
