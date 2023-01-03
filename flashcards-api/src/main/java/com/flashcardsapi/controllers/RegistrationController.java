package com.flashcardsapi.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.flashcardsapi.entities.User;
import com.flashcardsapi.entities.VerificationToken;
import com.flashcardsapi.services.EmailService;
import com.flashcardsapi.services.UserService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController { // todo: test all endpoints
    private final UserService userService;
    private EmailService emailService;

    public RegistrationController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    // @Value("${frontend.url}")
    private String frontendUrl;

    // todo: add password validation (number of characters and different symbols)
    @PostMapping()
    @ResponseBody
    public Map<String,String> registerNewUser(@Valid @RequestBody User newUser) {
        Map<String, String> test = new HashMap<>();
        User registeredUser = userService.registerNewUser(newUser);
        if (registeredUser != null) {
            test.put("message", "Registration successful!. check your email for verification letter");
            return test;
        }
        test.put("message", "Registration failed");
        return test;
    }

    @GetMapping("/confirm")
    public void verifyEmail(HttpServletResponse servletResponse, @RequestParam String token) {
        String message = "message: ";
        VerificationToken verificationToken = emailService.getToken(token);
        if (emailService.verifyToken(token) && verificationToken != null) {
            userService.confirmUser(verificationToken);
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
