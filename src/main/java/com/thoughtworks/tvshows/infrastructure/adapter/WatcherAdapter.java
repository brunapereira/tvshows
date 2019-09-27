package com.thoughtworks.tvshows.infrastructure.adapter;

import com.thoughtworks.tvshows.domain.watcher.Nickname;
import com.thoughtworks.tvshows.infrastructure.jpa.watcher.WatcherEntity;
import com.thoughtworks.tvshows.infrastructure.jpa.watcher.WatcherRepository;
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
