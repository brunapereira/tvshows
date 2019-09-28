package com.tvshows.domain.watcher;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
public class Nickname {
    private String nickname;

    @Override
    public String toString() {
        return nickname;
    }
}
