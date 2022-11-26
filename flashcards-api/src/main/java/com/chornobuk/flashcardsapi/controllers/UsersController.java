package com.chornobuk.flashcardsapi.controllers;

import com.chornobuk.flashcardsapi.entities.User;
import com.chornobuk.flashcardsapi.services.UserService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<User> updateUserPassowrd(@RequestParam Long userId, @RequestBody String newPassowrd) {
        User userWithNewPassword = userService.updatePassoword(newPassowrd, userId);
        if (userWithNewPassword == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(userWithNewPassword);
    }

    @PutMapping("user/{userId}/email")
    public ResponseEntity<String> updateUserEmail() {
        return ResponseEntity.ok("todo");
    }

    @PutMapping("user/{userId}/nickname")
    public ResponseEntity<String> updateUserNickname() {
        return ResponseEntity.ok("todo");
    }

    // this endpoint will be used for registration and nickname update
    @GetMapping("nickname/{nickname}")
    public ResponseEntity<String> getNicknameAvilability(@RequestParam String nickname) {
        String response = "isAvailable: " + userService.isNicknameAvailable(nickname);
        return ResponseEntity.ok(response);
    }

}
