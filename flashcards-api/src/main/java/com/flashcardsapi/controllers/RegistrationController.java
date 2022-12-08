package com.flashcardsapi.controllers;

import org.springframework.beans.factory.annotation.Value;
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
public class RegistrationController { // todo: test all endpoints
    private final UserService userService;
    private EmailService emailService;

    public RegistrationController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @Value("${frontend.url}")
    private String frontendUrl;

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
        String message = "message: ";
        if (emailService.verifyToken(token)) {
            servletResponse.setStatus(HttpServletResponse.SC_OK);
            message += "\"verified successufully!\"";
        } else {
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            message += "\"verification failed! New verification letter was sent!\"";
        }
        try {
            servletResponse.sendRedirect(frontendUrl);
            servletResponse.getWriter().write(message);
            servletResponse.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
