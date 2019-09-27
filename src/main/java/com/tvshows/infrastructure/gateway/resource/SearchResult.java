package com.tvshows.infrastructure.gateway.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchResult {
    private List<Series> data;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Series {
        private Long id;
        private String seriesName;
        private String overview;
    }
}
