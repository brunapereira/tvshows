package com.tvshows.infrastructure.jpa.watcher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WatcherRepository extends JpaRepository<WatcherEntity, Long> {
}
