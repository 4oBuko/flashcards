package com.chornobuk.flashcardsapi.controllers;

import com.chornobuk.flashcardsapi.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {
    @GetMapping("/user/{userId}")
    public ResponseEntity<String> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok("todo");
    }

    @PutMapping("/user")
    public ResponseEntity<String> updateUser(@RequestBody User userToUpdate) {
        return ResponseEntity.ok("todo");
    }
}
