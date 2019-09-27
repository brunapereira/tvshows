package com.tvshows.infrastructure.jpa.watcher;

import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "watcher")
public class WatcherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nickname;

    public WatcherEntity(String nickname) {
        this.nickname = nickname;
    }
}
