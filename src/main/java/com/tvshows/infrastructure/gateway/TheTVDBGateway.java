package com.tvshows.infrastructure.gateway;

import com.tvshows.infrastructure.gateway.resource.AuthenticationToken;
import com.tvshows.infrastructure.gateway.resource.LoginRequestBody;
import com.tvshows.infrastructure.gateway.resource.SearchResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@Component
@AllArgsConstructor
public class TheTVDBGateway {

    private final RestTemplate restTemplate;
    private final TheTVDBProperties theTVDBProperties;

    public SearchResult searchShows(String partialName) {
        String token = getToken();
        HttpEntity<String> request = new HttpEntity<>(getHttpHeaders(token));

        return restTemplate.exchange(getSearchSeriesUrl(partialName), HttpMethod.GET, request, SearchResult.class).getBody();
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

    private String getSearchSeriesUrl(String partialName) {
        return String.format("%s/search/series?name=%s", theTVDBProperties.getUrl(), partialName);
    }
}
