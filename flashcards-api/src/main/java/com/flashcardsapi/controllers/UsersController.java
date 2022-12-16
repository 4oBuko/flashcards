package com.flashcardsapi.controllers;

import lombok.AllArgsConstructor;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.flashcardsapi.entities.User;
import com.flashcardsapi.services.UserService;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        if (userId == null) {
            return ResponseEntity.badRequest().body(null);
        }
        User user = userService.getById(userId);
        if (user == null) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/user/{userId}/password")
    public ResponseEntity<User> updateUserPassowrd(@PathVariable Long userId,
            @RequestBody Map<String, String> requestBody) {
        String newPassowrd = requestBody.get("newPassword");
        if (newPassowrd == null) {
            return ResponseEntity.badRequest().body(null);
        }
        User userWithNewPassword = userService.updatePassoword(newPassowrd, userId);
        if (userWithNewPassword == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(userWithNewPassword);
    }

    @PutMapping("user/{userId}/email")
    public ResponseEntity<User> updateUserEmail(@PathVariable Long userId,
            @RequestBody Map<String, String> requestBody) {
        String newEmail = requestBody.get("newEmail");
        if (newEmail == null) {
            return ResponseEntity.badRequest().body(null);
        }
        User userWithNewEmail = userService.updateEmail(userId, newEmail);
        if (userWithNewEmail == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(userWithNewEmail);
    }

    @PutMapping("user/{userId}/nickname")
    public ResponseEntity<User> updateUserNickname(@PathVariable Long userId,
            @RequestBody Map<String, String> requestBody) {
        String newNickname = requestBody.get("newNickname");
        if (newNickname == null) {
            return ResponseEntity.badRequest().body(null);
        }
        User userWithNewNickname = userService.updateNickname(userId, newNickname);
        if (userWithNewNickname == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(userWithNewNickname);
    }

    // this endpoint will be used for registration and nickname update
    @GetMapping("nickname/{nickname}")
    public ResponseEntity<String> getNicknameAvilability(@PathVariable String nickname) {
        String response = "isAvailable: " + userService.isNicknameAvailable(nickname);
        return ResponseEntity.ok(response);
    }

}
