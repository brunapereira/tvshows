package com.tvshows.infrastructure.adapter.subscription;

import com.tvshows.domain.subscription.Subscription;
import com.tvshows.domain.subscription.Subscriptions;
import com.tvshows.domain.watcher.Nickname;
import com.tvshows.infrastructure.jpa.subscription.SubscriptionEntity;
import com.tvshows.infrastructure.jpa.subscription.SubscriptionRepository;
import com.tvshows.infrastructure.jpa.watcher.WatcherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SubscriptionAdapter {

    private final SubscriptionRepository subscriptionRepository;
    private final WatcherRepository watcherRepository;

    public Subscriptions findAllByNickname(Nickname nickname) {
        var watcher = watcherRepository.findByNickname(nickname.toString())
                .orElseThrow(() -> new RuntimeException());

        List<SubscriptionEntity> subscriptionEntities = subscriptionRepository.findAllByWatcherId(watcher.getId());

        return convertSubscriptionsEntityToDomain(subscriptionEntities);
    }

    private Subscriptions convertSubscriptionsEntityToDomain(List<SubscriptionEntity> subscriptionEntities) {
        List<Subscription> subscriptions = subscriptionEntities.stream()
                .map(entity -> new Subscription("", entity.getSeason(), entity.getEpisode()))
                .collect(Collectors.toList());

        return Subscriptions.of(subscriptions);
    }
}
