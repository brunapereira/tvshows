package com.thoughtworks.tvshows.application.watcher;

import com.thoughtworks.tvshows.domain.watcher.Nickname;
import com.thoughtworks.tvshows.infrastructure.adapter.WatcherAdapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WatcherService {

    private final WatcherAdapter watcherAdapter;

    public void createWatcher(Nickname nickname) {
        watcherAdapter.createWatcher(nickname);
    }
}
