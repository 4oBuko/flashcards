package com.flashcardsapi.controllers;

import com.flashcardsapi.dtos.flashcardsset.CreateFlashcardsSetDTO;
import com.flashcardsapi.dtos.flashcardsset.UpdateFlashcardsSetDTO;
import lombok.AllArgsConstructor;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import com.flashcardsapi.entities.db.FlashcardsSet;
import com.flashcardsapi.services.FlashcardsSetService;
import com.flashcardsapi.services.UserService;

import java.util.List;

@RestController
@RequestMapping("sets")
@AllArgsConstructor
public class FlashcardsSetController {
    private FlashcardsSetService flashcardsSetService;

    private UserService userService;


    @GetMapping("/{setId}")
    public FlashcardsSet getSetById(@PathVariable long setId, @AuthenticationPrincipal Jwt jwt) {
        return flashcardsSetService.getById(setId, jwt);
    }

    @GetMapping()
    public List<FlashcardsSet> getUserSets( @AuthenticationPrincipal Jwt jwt) {
        return flashcardsSetService.getUsersSets(jwt);
    }

    @PostMapping()
    public FlashcardsSet addNewSet(@Valid @RequestBody CreateFlashcardsSetDTO newSet, @AuthenticationPrincipal Jwt jwt) {
        return flashcardsSetService.create(newSet, jwt);
    }

    @PutMapping()
    public FlashcardsSet updateSet(@Valid @RequestBody  UpdateFlashcardsSetDTO dto, @AuthenticationPrincipal Jwt jwt) {
        return flashcardsSetService.update(dto, jwt);
    }

    @DeleteMapping("/{setId}")
    public String deleteSetById(@PathVariable long setId, @AuthenticationPrincipal Jwt jwt) {
        flashcardsSetService.deleteById(setId, jwt);
        return "tag was successfully deleted";
    }
}
