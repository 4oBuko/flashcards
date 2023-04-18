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

@RestController
@RequestMapping("sets")
@AllArgsConstructor
public class FlashcardsSetController {
    private FlashcardsSetService flashcardsSetService;

    private UserService userService;


    @GetMapping("/{setId}")
    public FlashcardsSet getSetById(@PathVariable long setId, @AuthenticationPrincipal Jwt jwt) {
        return flashcardsSetService.getSetById(setId, jwt);
    }

    @PostMapping()
    public FlashcardsSet addNewSet(@Valid @RequestBody CreateFlashcardsSetDTO newSet, @AuthenticationPrincipal Jwt jwt) {
        return flashcardsSetService.addNewSet(newSet, jwt);
    }

    @PutMapping()
    public FlashcardsSet updateSet(@Valid @RequestBody  UpdateFlashcardsSetDTO dto, @AuthenticationPrincipal Jwt jwt) {
        return flashcardsSetService.updateSet(dto, jwt);
    }

    @DeleteMapping("/{setId}")
    public String deleteSetById(@PathVariable long setId, @AuthenticationPrincipal Jwt jwt) {
        flashcardsSetService.deleteSetById(setId, jwt);
        return "tag was successfully deleted";
    }
}
