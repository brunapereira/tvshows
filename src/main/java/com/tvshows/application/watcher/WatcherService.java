package com.tvshows.application.watcher;

import com.tvshows.domain.watcher.Nickname;
import com.tvshows.infrastructure.adapter.watcher.WatcherAdapter;
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
