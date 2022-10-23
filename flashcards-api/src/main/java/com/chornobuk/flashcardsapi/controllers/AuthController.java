package com.chornobuk.flashcardsapi.controllers;

import com.chornobuk.flashcardsapi.entities.User;
import com.chornobuk.flashcardsapi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController {
    private UserService userService;

    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
//        todo: add authentication here
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
//            );
//        } catch (BadCredentialsException e) {
//            System.out.println("here");
//        }
        return ResponseEntity.ok("todo");
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshAccesstoken() {
        return ResponseEntity.ok("todo");
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@RequestBody User newUser) {
        User registeredUser = userService.registerNewUser(newUser);
        if (registeredUser != null) {
            return ResponseEntity.ok("Registration successful!");
        }
        return ResponseEntity.badRequest().body("Registration failed");
    }
}
