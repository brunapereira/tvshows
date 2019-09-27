package com.thoughtworks.tvshows.controller.watcher;

import com.thoughtworks.tvshows.application.watcher.WatcherService;
import com.thoughtworks.tvshows.controller.watcher.resource.CreateWatcherRequestBody;
import com.thoughtworks.tvshows.domain.watcher.Nickname;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WatcherController {

    private final WatcherService watcherService;

    @PostMapping("/watchers")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewWatcher(@RequestBody CreateWatcherRequestBody createWatcherRequestBody) {
        Nickname nickname = new Nickname(createWatcherRequestBody.getNickname());

        watcherService.createWatcher(nickname);
    }
}
