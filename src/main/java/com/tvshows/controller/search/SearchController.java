package com.tvshows.controller.search;

import com.tvshows.infrastructure.gateway.TheTVDBGateway;
import com.tvshows.infrastructure.gateway.resource.SearchResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SearchController {

    private TheTVDBGateway theTVDBGateway;

    @GetMapping("/search")
    public SearchResult searchShows(@RequestParam String partialName) {
        return theTVDBGateway.searchShows(partialName);
    }
}
