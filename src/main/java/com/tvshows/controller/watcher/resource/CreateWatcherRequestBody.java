package com.tvshows.controller.watcher.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CreateWatcherRequestBody {
    @Getter
    private String nickname;
}
