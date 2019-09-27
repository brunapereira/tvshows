package com.thoughtworks.tvshows.infrastructure.gateway;

import com.thoughtworks.tvshows.infrastructure.gateway.resource.AuthenticationToken;
import com.thoughtworks.tvshows.infrastructure.gateway.resource.LoginRequestBody;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@Component
public class TheTVDBGateway {

    private final RestTemplate restTemplate;
    private final TheTVDBProperties theTVDBProperties;

    public TheTVDBGateway(TheTVDBProperties theTVDBProperties) {
        this.restTemplate = new RestTemplate();
        this.theTVDBProperties = theTVDBProperties;
    }

    public String getShows() {
        String token = getToken();
        HttpEntity<String> request = new HttpEntity<>(getHttpHeaders(token));

        ResponseEntity<String> exchange = restTemplate.exchange(getEndpointUrl(), HttpMethod.GET, request, String.class);

        return exchange.getBody();
    }

    private String getToken() {
        LoginRequestBody loginRequestBody = new LoginRequestBody(theTVDBProperties.getUsername(),
                theTVDBProperties.getUserkey(), theTVDBProperties.getApikey());

        return restTemplate.postForEntity(getLoginUrl(), loginRequestBody, AuthenticationToken.class)
                .getBody().getToken();
    }

    private HttpHeaders getHttpHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8.toString());
        return headers;
    }

    private String getLoginUrl() {
        return theTVDBProperties.getUrl() + "/login";
    }

    private String getEndpointUrl() {
        return theTVDBProperties.getUrl() + "/episodes/1";
    }
}
