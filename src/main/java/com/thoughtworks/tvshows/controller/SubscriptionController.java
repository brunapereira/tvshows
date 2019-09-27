package com.thoughtworks.tvshows.controller;

import com.thoughtworks.tvshows.infrastructure.gateway.TheTVDBGateway;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SubscriptionController {

    private final TheTVDBGateway theTVDBGateway;

    @GetMapping("/subscriptions")
    @ResponseStatus(HttpStatus.OK)
    public String getSubscriptions() {
        return theTVDBGateway.getShows();
    }
}
