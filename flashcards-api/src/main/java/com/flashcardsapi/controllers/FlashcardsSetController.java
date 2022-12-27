package com.flashcardsapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import com.flashcardsapi.entities.FlashcardsSet;
import com.flashcardsapi.entities.User;
import com.flashcardsapi.services.FlashcardsSetsService;
import com.flashcardsapi.services.UserService;

@RestController
@RequestMapping("sets")
@AllArgsConstructor
public class FlashcardsSetController {
    private FlashcardsSetsService flashcardsSetsService;

    private UserService userService;

    @GetMapping("/{setId}")
    public FlashcardsSet getSetById(@PathVariable long setId) {
        return flashcardsSetsService.getSetById(setId);
    }

    @PostMapping()
    public FlashcardsSet addNewSet(@RequestBody FlashcardsSet newSet, @AuthenticationPrincipal Jwt principal) {
        long userId = (long) principal.getClaims().get("id");
        User testUser = userService.getById(userId);
        // todo: replace with spring boot - validation
        // if (newSet.getName() == null
        //         || newSet.getQuestionLanguage() == null
        //         || newSet.getAnswerLanguage() == null
        //         || newSet.getFlashcards() == null) {
        //     return ResponseEntity.badRequest().body(null);
        // }
        return flashcardsSetsService.addNewSet(newSet, testUser);
    }// todo: should I use jwt for getting info about user?

    @PutMapping()
    public ResponseEntity<FlashcardsSet> updateSet(@RequestBody FlashcardsSet setToUpdate) {
        try {
            FlashcardsSet updatedSet = flashcardsSetsService.updateSet(setToUpdate);
            return ResponseEntity.ok(updatedSet);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{setId}")
    public ResponseEntity<String> deleteSetById(@PathVariable long setId) {
        flashcardsSetsService.deleteSetById(setId);
        // todo: check if the user can delete this tag
        return ResponseEntity.ok("tag was successfully deleted");
    }
}
