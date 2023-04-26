package com.flashcardsapi.controllers;

import com.flashcardsapi.dtos.user.CreateUserDTO;
import com.flashcardsapi.entities.ConfirmationResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.flashcardsapi.entities.db.VerificationToken;
import com.flashcardsapi.services.EmailService;
import com.flashcardsapi.services.UserService;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

@Controller
@Log4j2
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;
    private final EmailService emailService;

    public RegistrationController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping()
    @ResponseBody
    public Map<String, String> registerNewUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        Map<String, String> test = new HashMap<>();
        userService.registerNewUser(createUserDTO);
        test.put("message", "Registration successful!. check your email for verification letter");
        return test;
    }

    @GetMapping("/confirm")
    public ConfirmationResult verifyEmail(@RequestParam String token) {
        VerificationToken verificationToken = emailService.getToken(token);
        if (emailService.verifyToken(token)) {
            userService.confirmUser(verificationToken);
            return new ConfirmationResult(true, "verified successfully!");
        } else {
            emailService.sendVerificationLetter(verificationToken.getUser());
            return new ConfirmationResult(false, "verification failed! Check your email for new verification letter!");
        }
    }
}
