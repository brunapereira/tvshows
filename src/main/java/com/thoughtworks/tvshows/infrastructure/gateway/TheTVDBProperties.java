package com.thoughtworks.tvshows.infrastructure.gateway;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("thedvdb.service")
public class TheTVDBProperties {

    private String username;
    private String userkey;
    private String apikey;
    private String url;

    String getUserkey() {
        return userkey;
    }

    public String getApikey() {
        return apikey;
    }

    public String getUsername() {
        return username;
    }

    public String getUrl() {
        return url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
