package com.chornobuk.flashcardsapi.controllers;

import com.chornobuk.flashcardsapi.entities.FlashcardsSet;
import com.chornobuk.flashcardsapi.entities.User;
import com.chornobuk.flashcardsapi.services.FlashcardsSetsService;
import com.chornobuk.flashcardsapi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sets")
@AllArgsConstructor
public class FlashcardsSetController {
    private FlashcardsSetsService flashcardsSetsService;

    private UserService userService;

    @GetMapping("/set/{setId}")
    public ResponseEntity<FlashcardsSet> getSetById(@PathVariable long setId) {
        FlashcardsSet set = flashcardsSetsService.getSetById(setId);
        return ResponseEntity.ok(set);
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<FlashcardsSet>> getUserSets(@PathVariable Long userId) {
        User user = userService.getById(userId);
        if (user == null) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(flashcardsSetsService.getSetsByUser(user));
    }

    @PostMapping()
    public ResponseEntity<String> addNewSet(@RequestBody FlashcardsSet newSet, @AuthenticationPrincipal Jwt principal) {
        long userId = (long) principal.getClaims().get("id");
        User testUser = userService.getById(userId);
        if (newSet.getName() == null
                || newSet.getQuestionLanguage() == null
                || newSet.getAnswerLanguage() == null
                || newSet.getFlashcards() == null) {
            return ResponseEntity.badRequest().body("bad request");
        }
        flashcardsSetsService.addNewSet(newSet, testUser);
        return ResponseEntity.ok("set was successfully added!");
    }

//    todo: test it
    @PutMapping()
    public ResponseEntity<FlashcardsSet> updateSetById(@RequestBody FlashcardsSet setToUpdate) {
        try {
            FlashcardsSet updatedSet = flashcardsSetsService.updateSet(setToUpdate);
            return ResponseEntity.ok(updatedSet);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //    todo: test it
    @DeleteMapping("/{setId}")
    public ResponseEntity<String> deleteTagById(@PathVariable long setId) {
        flashcardsSetsService.deleteSetById(setId);
//        todo: check if the user can delete this tag
        return ResponseEntity.ok("tag was successfully deleted");
    }
}
