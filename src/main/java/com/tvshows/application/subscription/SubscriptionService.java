package com.tvshows.application.subscription;

import com.tvshows.domain.subscription.Subscriptions;
import com.tvshows.domain.watcher.Nickname;
import com.tvshows.infrastructure.adapter.subscription.SubscriptionAdapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubscriptionService {

    private final SubscriptionAdapter subscriptionAdapter;

    public Subscriptions getSubscriptionsByWatcher(Nickname nickname) {
        return subscriptionAdapter.findAllByNickname(nickname);
    }
}
