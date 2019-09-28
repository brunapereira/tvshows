package com.tvshows.infrastructure.jpa.watcher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "watcher")
public class WatcherEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nickname;

    public WatcherEntity(String nickname) {
        this.nickname = nickname;
    }
}
