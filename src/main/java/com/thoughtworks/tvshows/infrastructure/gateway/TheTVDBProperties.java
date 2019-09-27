package com.thoughtworks.tvshows.infrastructure.gateway;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("thedvdb.service")
public class TheTVDBProperties {

    private String username;
    private String userkey;
    private String apikey;
    private String url;
}
