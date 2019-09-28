package com.tvshows.domain.subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class Subscriptions {
    @Getter
    private List<Subscription> subscriptions;

    public static Subscriptions of(List<Subscription> subscriptionsList) {
        return new Subscriptions(subscriptionsList);
    }
}
