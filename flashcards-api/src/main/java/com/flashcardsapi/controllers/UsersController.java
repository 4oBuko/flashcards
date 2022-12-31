package com.flashcardsapi.controllers;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.flashcardsapi.entities.FlashcardsSet;
import com.flashcardsapi.entities.Tag;
import com.flashcardsapi.entities.User;
import com.flashcardsapi.services.FlashcardsSetsService;
import com.flashcardsapi.services.TagsService;
import com.flashcardsapi.services.UserService;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final UserService userService;
    private final FlashcardsSetsService setsService;
    private final TagsService tagsService;

    // todo: error handling if user wasn't found
    // todo: I can take user id for update email, password or nickname from jwt
    @GetMapping("/{userId}")
    public User getUserById(@Valid @PathVariable Long userId) {
        return userService.getById(userId);
    }

    @PutMapping("/{userId}/password")
    public User updateUserPassowrd(@PathVariable Long userId, @RequestBody Map<String, String> requestBody) {
        String newPassowrd = requestBody.get("newPassword");
        if (newPassowrd == null) {
            // return ResponseEntity.badRequest().body(null);
            return null;
        }
        User userWithNewPassword = userService.updatePassoword(newPassowrd, userId);
        if (userWithNewPassword == null) {
            return null;
            // return ResponseEntity.badRequest().body(null);
        }
        return userWithNewPassword;
    }

    @PutMapping("{userId}/email")
    public User updateUserEmail(@PathVariable Long userId, @RequestBody Map<String, String> requestBody) {
        String newEmail = requestBody.get("newEmail");
        if (newEmail == null) {
            // return ResponseEntity.badRequest().body(null);
            // return null;
        }
        User userWithNewEmail = userService.updateEmail(userId, newEmail);
        if (userWithNewEmail == null) {
            // return ResponseEntity.badRequest().body(null);
            return null;
        }
        return userWithNewEmail;
    }

    @PutMapping("{userId}/nickname")
    public User updateUserNickname(@PathVariable Long userId, @RequestBody Map<String, String> requestBody) {
        String newNickname = requestBody.get("newNickname");
        if (newNickname == null) {
            // return ResponseEntity.badRequest().body(null);
            return null;
        }
        User userWithNewNickname = userService.updateNickname(userId, newNickname);
        if (userWithNewNickname == null) {
            // return ResponseEntity.badRequest().body(null);
            return null;
        }
        return userWithNewNickname;
    }

    // this endpoint will be used for registration and nickname update
    // todo: fix. After remove response entity endpoint returns 404 error
    @GetMapping("/nickname/{nickname}")
    public Map.Entry<String, Boolean> getNicknameAvilability(@PathVariable String nickname) {
        // String response = "isAvailable: " + userService.isNicknameAvailable(nickname);
        return Map.entry("isAvailable", userService.isNicknameAvailable(nickname));
    }

    @GetMapping("/{userId}/sets")
    public List<FlashcardsSet> getUserSets(@PathVariable Long userId) {
        User user = userService.getById(userId);
        // todo: replace it
        if (user == null) {
            return null;
        }
        return setsService.getSetsByUser(user);
    }

    @GetMapping("/{userId}/tags")
    public List<Tag> getUserTags(@PathVariable long userId) {
        User user = userService.getById(userId);
        if (user == null) {
            return null;
        }
        return tagsService.getTagsByUser(user);
    }
}
