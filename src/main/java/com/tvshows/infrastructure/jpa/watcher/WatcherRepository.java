package com.tvshows.infrastructure.jpa.watcher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WatcherRepository extends JpaRepository<WatcherEntity, Long> {

    Optional<WatcherEntity> findByNickname(@Param("nickname") String nickname);
}
