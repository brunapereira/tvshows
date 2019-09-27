package com.tvshows.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;

public class AuthenticationFilter extends OncePerRequestFilter {

    private static final String TOKEN_HEADER_KEY = "authentication-token";

    private static final List<String> WHITELISTED_SWAGGER_URIS = asList(
            "/v2/api-docs",
            "/swagger-resources/**",
            "/configuration/security/**",
            "/swagger-ui.html",
            "/webjars/**");

    private final List<String> authKeys;

    AuthenticationFilter(String authKeyConfig) {
        this.authKeys = extractAuthKeysFromConfig(authKeyConfig);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        if (requestedUriIsWhitelisted(request) || tokenIsValid(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        response.sendError(SC_FORBIDDEN, "Authentication Failed");
    }

    private boolean requestedUriIsWhitelisted(HttpServletRequest request) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        return WHITELISTED_SWAGGER_URIS.stream()
                .anyMatch(prefix -> antPathMatcher.matchStart(prefix, request.getRequestURI()));
    }

    private boolean tokenIsValid(HttpServletRequest request) {
        String providedKey = request.getHeader(TOKEN_HEADER_KEY);
        return !StringUtils.isEmpty(providedKey) && authKeys.contains(providedKey);
    }

    private static List<String> extractAuthKeysFromConfig(String authKeyConfig) {
        final ObjectMapper objectMapper = new ObjectMapper();
        final TypeFactory typeFactory = objectMapper.getTypeFactory();

        if (authKeyConfig == null) {
            return emptyList();
        }

        try {
            return objectMapper.readValue(authKeyConfig, typeFactory.constructCollectionType(List.class, String.class));
        } catch (IOException e) {
            return singletonList(authKeyConfig);
        }
    }
}
