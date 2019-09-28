package com.tvshows.infrastructure.jpa.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {
    List<SubscriptionEntity> findAllByWatcherId(@Param("watcherId") Long watcherId);
}
