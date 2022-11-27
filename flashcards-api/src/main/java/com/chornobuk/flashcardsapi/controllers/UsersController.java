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
    public ResponseEntity<User> updateUserEmail(@RequestParam Long userId, @RequestBody String newEmail) {
        User userWithNewEmail = userService.updateEmail(userId, newEmail);
        if(userWithNewEmail == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(userWithNewEmail);
    }

    @PutMapping("user/{userId}/nickname")
    public ResponseEntity<User> updateUserNickname(@RequestParam Long userId, @RequestBody String newNickname) {
        User userWithNewNickname = userService.updateNickname(userId,newNickname);
        if(userWithNewNickname == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(userWithNewNickname);
    }

    // this endpoint will be used for registration and nickname update
    @GetMapping("nickname/{nickname}")
    public ResponseEntity<String> getNicknameAvilability(@RequestParam String nickname) {
        String response = "isAvailable: " + userService.isNicknameAvailable(nickname);
        return ResponseEntity.ok(response);
    }

}
