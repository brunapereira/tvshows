package com.tvshows.infrastructure.adapter.watcher;

import com.tvshows.domain.watcher.Nickname;
import com.tvshows.infrastructure.jpa.watcher.WatcherEntity;
import com.tvshows.infrastructure.jpa.watcher.WatcherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WatcherAdapter {

    private final WatcherRepository watcherRepository;

    public void createWatcher(Nickname nickname) {
        var watcherEntity = new WatcherEntity(nickname.toString());

        watcherRepository.save(watcherEntity);
    }
}
