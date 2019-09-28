package com.tvshows.domain.subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Subscription {
    private String seriesName;
    private Integer season;
    private Integer episode;
}
