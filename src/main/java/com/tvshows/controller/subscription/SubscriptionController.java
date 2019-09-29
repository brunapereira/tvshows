package com.tvshows.controller.subscription;

import com.tvshows.application.subscription.SubscriptionService;
import com.tvshows.domain.subscription.Subscriptions;
import com.tvshows.domain.watcher.Nickname;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/watchers/{nickname}/subscriptions")
    @ResponseStatus(HttpStatus.OK)
    public Subscriptions getSubscriptions(@PathVariable Nickname nickname) {
        return subscriptionService.getSubscriptionsByWatcher(nickname);
    }
}
