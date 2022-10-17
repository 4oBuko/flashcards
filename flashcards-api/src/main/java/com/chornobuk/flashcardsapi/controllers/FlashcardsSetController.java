package com.chornobuk.flashcardsapi.controllers;

import com.chornobuk.flashcardsapi.entities.FlashcardsSet;
import com.chornobuk.flashcardsapi.entities.User;
import com.chornobuk.flashcardsapi.services.FlashcardsSetsService;
import com.chornobuk.flashcardsapi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sets")
@AllArgsConstructor
public class FlashcardsSetController {
    private FlashcardsSetsService flashcardsSetsService;

    private UserService userService;

    @GetMapping("/set/{setId}")
    public ResponseEntity<String> getSetById() {
        return ResponseEntity.ok("todo");
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<FlashcardsSet>> getUserTags(@PathVariable Long userId) {
        User user = userService.getById(userId);
        if(user == null) {
//            todo: user correct method for response
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(flashcardsSetsService.getSetsByUser(user));
    }

    @PostMapping()
    public ResponseEntity<String> addNewSet(@RequestBody FlashcardsSet newSet) {
//        todo: get userId from access token
        User testUser = userService.getById(1L);
        flashcardsSetsService.addNewSet(newSet,testUser);
        return ResponseEntity.ok("todo");
    }

    @PutMapping("/{setId}")
    public ResponseEntity<String> updateSetById() {
        return ResponseEntity.ok("todo");
    }

    @DeleteMapping("/{setId}")
    public ResponseEntity<String> deleteTagById() {
        return ResponseEntity.ok("todo");
    }


}
