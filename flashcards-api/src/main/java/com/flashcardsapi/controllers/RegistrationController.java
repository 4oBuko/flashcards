package com.flashcardsapi.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.flashcardsapi.entities.User;
import com.flashcardsapi.services.EmailService;
import com.flashcardsapi.services.UserService;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private EmailService emailService;

    @PostMapping()
    public ResponseEntity<String> registerNewUser(@RequestBody User newUser) {
        User registeredUser = userService.registerNewUser(newUser);
        if (registeredUser != null) {
            return ResponseEntity.ok("Registration successful!");
        }
        return ResponseEntity.badRequest().body("Registration failed");
    }

    // todo: test it
    @GetMapping("/confirm")
    public void verifyEmail(HttpServletResponse servletResponse, @RequestParam String token) {
        String message = "message: ";
        if (emailService.verifyToken(token)) {
            servletResponse.setStatus(HttpServletResponse.SC_OK);
            message += "\"verified successufully!\"";
        } else {
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            message += "\"verification failed! New verification letter was sent!\"";
        }
        try {
            servletResponse.sendRedirect("frontendUrl");
            servletResponse.getWriter().write(message);
            servletResponse.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
