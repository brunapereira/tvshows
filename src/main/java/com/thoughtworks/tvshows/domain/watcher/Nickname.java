package com.thoughtworks.tvshows.domain.watcher;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
public class Nickname {
    @Getter
    private String nickname;

    @Override
    public String toString() {
        return nickname;
    }
}
