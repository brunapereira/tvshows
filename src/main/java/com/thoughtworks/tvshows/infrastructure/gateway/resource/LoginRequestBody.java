package com.thoughtworks.tvshows.infrastructure.gateway.resource;

public class LoginRequestBody {

    private String username;
    private String userkey;
    private String apikey;

    public LoginRequestBody(String username, String userkey, String apikey) {
        this.username = username;
        this.userkey = userkey;
        this.apikey = apikey;
    }

    public String getUsername() {
        return username;
    }

    public String getUserkey() {
        return userkey;
    }

    public String getApikey() {
        return apikey;
    }
}
