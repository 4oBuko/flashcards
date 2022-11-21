package com.chornobuk.flashcardsapi.controllers;

import com.chornobuk.flashcardsapi.entities.User;
import com.chornobuk.flashcardsapi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<String> registerNewUser(@RequestBody User newUser) {
        User registeredUser = userService.registerNewUser(newUser);
        if (registeredUser != null) {
            return ResponseEntity.ok("Registration successful!");
        }
        return ResponseEntity.badRequest().body("Registration failed");
    }

    @GetMapping("/confirm")
    public void verifyEmail(HttpServletResponse servletResponse, @RequestParam String token) {
//        todo: confirm token
//          send redirect to front-end if token was verified
//          if token is expired send a new one
    }
}
