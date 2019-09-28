package com.tvshows.infrastructure.jpa.subscription;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "subscription")
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long watcherId;
    private Integer seriesId;
    @Getter
    private Integer season;
    @Getter
    private Integer episode;

    public SubscriptionEntity(Long watcherId, Integer seriesId, Integer season, Integer episode) {
        this.watcherId = watcherId;
        this.seriesId = seriesId;
        this.season = season;
        this.episode = episode;
    }
}
