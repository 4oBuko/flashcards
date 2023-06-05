package com.flashcardsapi.controllers;

import com.flashcardsapi.entities.SearchResult;
import com.flashcardsapi.services.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {

    private SearchService searchService;

    @GetMapping()
    public SearchResult search(@RequestParam String name, @AuthenticationPrincipal Jwt jwt) {
        return searchService.searchByName(name, jwt);
    }
}
